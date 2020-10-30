from flask import Flask, render_template, request
from face_detect import processVideo
app = Flask(__name__)

baseUrl = './static/images/'
@app.route('/')
def hello_world():
   return render_template('home.html')

@app.route('/processVideo',methods = ['POST'])
def process_video():
   print("hello")
   modelUrl = "./static/model/shape_predictor_68_face_landmarks.dat"
   videoUrl = baseUrl + request.data.decode("utf-8")
   print(videoUrl)
   processVideo(modelUrl,videoUrl)
   return "hello"


if __name__ == '__main__':
   app.run(debug = True,port=5003)
