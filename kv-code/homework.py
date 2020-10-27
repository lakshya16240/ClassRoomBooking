import numpy as np
import dlib
import pdb;
import cv2
import argparse
import skvideo.io as io

class Options():

    def __init__(self):
        parser = argparse.ArgumentParser(description='Face and Gaze Detector',
                                        formatter_class=argparse.ArgumentDefaultsHelpFormatter)

        parser.add_argument('--vid_path', type=str, default="videocv.webm" , help='Video path.')
        parser.add_argument('--h_margin', type=float, default=0.1 , help='Acceptable Height Margin wrt frame')
        parser.add_argument('--w_margin', type=float, default=0.2 , help='Acceptable Width Margin wrt frame')
        self.parser = parser

    def parse(self):
        return self.parser.parse_args()


def eye_on_mask(mask, side, shape):
    points = [shape[i] for i in side]
    points = np.array(points, dtype=np.int32)
    mask = cv2.fillConvexPoly(mask, points, 255)
    l = points[0][0]
    t = (points[1][1]+points[2][1])//2
    r = points[3][0]
    b = (points[4][1]+points[5][1])//2
    return mask, [l, t, r, b]

def find_eyeball_position(end_points, cx, cy):
    x_ratio = (end_points[0] - cx)/(cx - end_points[2])
    y_ratio = (cy - end_points[1])/(end_points[3] - cy)
    if x_ratio > 3:
        return 1
    elif x_ratio < 0.33:
        return 2
    elif y_ratio < 0.33:
        return 3
    else:
        return 0

def shape_to_np(shape, dtype="int"):
	coords = np.zeros((68, 2), dtype=dtype)

	for i in range(0, 68):
		coords[i] = (shape.part(i).x, shape.part(i).y)
	return coords

def contouring(thresh, mid, img, end_points, right=False):
    cnts, _ = cv2.findContours(thresh, cv2.RETR_EXTERNAL,cv2.CHAIN_APPROX_NONE)
    try:
        cnt = max(cnts, key = cv2.contourArea)
        eye_area = cv2.contourArea(cnt)
        M = cv2.moments(cnt)
        cx = int(M['m10']/M['m00'])
        cy = int(M['m01']/M['m00'])
        if right:
            cx += mid
        cv2.circle(img, (cx, cy), 4, (0, 0, 255), 2)
        pos = find_eyeball_position(end_points, cx, cy)
        return pos, eye_area
    except:
        pass

def analyse_eye_pos(left,right):
	if left != 0 or right!=0:
		if left == 1 or right == 1:
			return "left"
		elif left == 2 or right == 2:
			return "right"
		elif left == 3 or right == 3:
			return "up"
	else:
		return "normal"

def rect_to_bb(rect):
	x = rect.left()
	y = rect.top()
	w = rect.right() - x
	h = rect.bottom() - y
	return (x, y, w, h)

def check_center(bbox, w_margin, h_margin, height, width):
	if bbox[0]>w_margin and bbox[0]+bbox[2]<width-w_margin:
		if bbox[1]>h_margin and bbox[1]+bbox[3]<height-h_margin:
			return True
	return False

def pre_process_video(detector, predictor, vid_path):
	
	vidcap = cv2.VideoCapture(vid_path)
	print(vidcap.isOpened() == True)
	
	total_frames = int(vidcap.get(cv2.CAP_PROP_FRAME_COUNT))
	print(total_frames)

	# temp = io.vreader(vid_path)
	# print(temp)
	# for frame in temp:
	# 	print(frame)

	
	kernel = np.ones((9, 9), np.uint8)
	left = [36, 37, 38, 39, 40, 41]
	right = [42, 43, 44, 45, 46, 47]

	count=0
	#Stores center validations
	center_validations = []
	#Stores Face detected validations
	face_detected = []
	#Stores Face size validations
	face_size_valid = []
	#Stores Eye-ball positions
	eye_ball_position = []
	#Stores frames with more than 1 faces
	multiple_faces_frames = []
	#Stores eye areas through frames
	eye_area = []

	while count<total_frames:
		count+=1
		print(count)
		success,img = vidcap.read()
		if count%5==0:
			height, width, c = img.shape
			width_margin = int(width * 0.2)
			height_margin = int(height * 0.1)
			gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
			rects = detector(gray, 1)
			REMOVE_FACTOR = 4
			#ignoring smaller faces detected.
			max_area_rect = 0
			for rect in rects:
				if (rect.right()-rect.left())*(rect.bottom()-rect.top())> max_area_rect:
					max_area_rect = (rect.right()-rect.left())*(rect.bottom()-rect.top())

			final_rects = [rect for rect in rects if REMOVE_FACTOR*(rect.right()-rect.left())*(rect.bottom()-rect.top()) > max_area_rect]
			# print(final_rects)
			if len(final_rects)==0 and len(rects)!=0:
				center_validations.append(False)
				face_detected.append(False)
				face_size_valid.append(False)
				eye_ball_position.append("NA")
			elif len(final_rects)==0:
				center_validations.append(False)
				face_detected.append(False)
				face_size_valid.append(True)
				eye_ball_position.append("NA")
			else:

				if len(final_rects)>1:
					center_validations.append(False)
					face_detected.append(True)
					face_size_valid.append(True)
					eye_ball_position.append("NA")
					multiple_faces_frames.append(count)
				else:
					rect = final_rects[0]
					shape = predictor(gray, rect)
					shape = shape_to_np(shape)
					(x, y, w, h) = rect_to_bb(rect)
					print([x,y,w,h])
					
					isCenter = check_center([x,y,w,h], width_margin, height_margin, height, width)
					center_validations.append(isCenter)

					face_detected.append(True)
					face_size_valid.append(True)

					#Eye-Gaze estimate
					mask = np.zeros(img.shape[:2], dtype=np.uint8)
					mask, end_points_left = eye_on_mask(mask, left, shape)
					mask, end_points_right = eye_on_mask(mask, right, shape)
					mask = cv2.dilate(mask, kernel, 5)

					eyes = cv2.bitwise_and(img, img, mask=mask)
					mask = (eyes == [0, 0, 0]).all(axis=2)
					eyes[mask] = [255, 255, 255]
					mid = (shape[42][0] + shape[39][0]) // 2
					eyes_gray = cv2.cvtColor(eyes, cv2.COLOR_BGR2GRAY)
					_, thresh = cv2.threshold(eyes_gray, 100, 255, cv2.THRESH_BINARY)
					thresh = cv2.erode(thresh, None, iterations=2)
					thresh = cv2.dilate(thresh, None, iterations=4)
					thresh = cv2.medianBlur(thresh, 3)
					thresh = cv2.bitwise_not(thresh)

					try:
						left_eyeball_pos, area_l = contouring(thresh[:, 0:mid], mid, img, end_points_left)
						right_eyeball_pos, area_r = contouring(thresh[:, mid:], mid, img, end_points_right,True)
						eye_pos = analyse_eye_pos(left_eyeball_pos, right_eyeball_pos)
						eye_ball_position.append(eye_pos)
						eye_area.append((area_l,area_r))
					except:
						eye_ball_position.append("NA")
						eye_area.append((0,0))
					#############################
					#TESTING
					#Check margins and detections

					# cv2.line(img, (width_margin,0), (width_margin,height), (255, 255, 255), 4)
					# cv2.line(img, (width - width_margin,0), (width - width_margin,height), (255, 255, 255), 4)
					# cv2.line(img, (0,height_margin), (width, height_margin), (255, 255, 255), 4)
					# cv2.line(img, (0,height - height_margin), (width, height - height_margin), (255, 255, 255), 4)
					# for (x, y) in shape:
					# 	cv2.circle(img, (x, y), 2, (255, 0, 0), -1)

			# cv2.imwrite("frame_"+str(count)+".jpg",img)
		else:
			continue
	
	vidcap.release()

	return center_validations, face_detected, face_size_valid, eye_ball_position, multiple_faces_frames, eye_area

def final_analysis(center_validations, face_detected, face_size_valid, eye_ball_position, multiple_faces_frames, eye_area):

	center_valid = False
	face_detect = False
	face_size = False
	eye_gaze = False

	if sum(center_validations)>=0.8*len(center_validations):
		center_valid = True
	if sum(face_detected) >= 0.8*len(face_detected):
		face_detect = True
	if sum(face_size_valid) >= 0.8*len(face_size_valid):
		face_size = True
	if eye_ball_position.count("normal") >= 0.8*len(eye_ball_position):
		eye_gaze = True


	# vidcap = cv2.VideoCapture(vid_path)
	

	if center_valid and face_detect and face_size and eye_gaze and len(multiple_faces_frames)<2:
		print("Accepted!")
	else:
		print("Rejected!")

		if not eye_gaze:
			print("Please look more into the camera !")
		if not face_size:
			print("Please come closer to the camera !")
		if not face_detect:
			print("Face is not detectable, please be in frame or check for lighting or occlusion ")
		if not center_valid:
			print("Please allign your camera so that you are in center of the frame")

		if len(multiple_faces_frames)>=2:
			print("Please ensure that multiple faces are not visible in the video!")

	# pdb.set_trace()

def processVideo(modelUrl,videoUrl):
	print(videoUrl,modelUrl)
	detector = dlib.get_frontal_face_detector()
	predictor = dlib.shape_predictor(modelUrl)

	center_validations, face_detected, face_size_valid, eye_ball_position, multiple_faces_frames, eye_area = pre_process_video(detector, predictor, videoUrl)
	final_analysis(center_validations, face_detected, face_size_valid, eye_ball_position, multiple_faces_frames, eye_area)

def extract_thumbnail():
	pass


if __name__ == '__main__':
	args = Options().parse()

	detector = dlib.get_frontal_face_detector()
	predictor = dlib.shape_predictor('./static/model/shape_predictor_68_face_landmarks.dat')

	center_validations, face_detected, face_size_valid, eye_ball_position, multiple_faces_frames, eye_area = pre_process_video(detector, predictor, args.vid_path)
	final_analysis(center_validations, face_detected, face_size_valid, eye_ball_position, multiple_faces_frames, eye_area)

	
