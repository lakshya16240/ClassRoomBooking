 @FXML
    void DisApproveRequests(ActionEvent event) throws IOException, ClassNotFoundException {
//        System.out.println("Dis-Approve request called");
        ObservableList<Requests> DisApprovedRequests = FXCollections.observableArrayList();;
        DisApprovedRequests = requestTable.getSelectionModel().getSelectedItems();
//        System.out.println("req = " + ApprovedRequests);
        ArrayList<Requests> AllRequests = new ArrayList<Requests>();
        AllRequests = Controller.deserializeArray();
        int cnt = DisApprovedRequests.size() - 1;
//        System.out.println("dis - approved requests = " + DisApprovedRequests);
//        System.out.println("requests = " + AllRequests);
        ArrayList<Requests> DisAppReq = new ArrayList<Requests>();

        for (int i = 0; i < DisApprovedRequests.size(); i++) {
            DisAppReq.add(DisApprovedRequests.get(i));
        }

        while (cnt >= 0) {
            Requests current_req = DisAppReq.get(cnt);
//                User  userobj = clgobj.getAllUsersMap().get(current_req.getUser().getName());
//            User userobj = current_req.getUser();
            User userobj = clgobj.getAllUsersMap().get(current_req.getUser().getEmailId());

            System.out.println("user check " + userobj);

            for (int i = 0; i < userobj.getRequests().size(); i++) {
                if (userobj.getRequests().get(i).equals(current_req)) {
                    userobj.getRequests().get(i).setStatus("Rejected");
                }
            }
            System.out.println("All requests on pressing DisApproved: " + userobj.getRequests());
            clgobj.getAllUsersMap().put(current_req.getUser().getEmailId(), userobj);
//            System.out.println("The actual check " + clgobj.getAllUsersMap().get("ab").getRequests());

//            System.out.println("here will be the error " + (clgobj.getAllUsersMap().get("ab") == current_req.getUser()));
//            System.out.println("one more check when I did jugaad " + current_req.getStatus());
//            current_req.setStatus("Approved");
            for (int i = 0; i < AllRequests.size(); i++) {
//                System.out.println(AllRequests.get(i) + " is not equal to " + ApprovedRequests.get(0));
//                Boolean t = Requests.get(i).getReason().equals(ApprovedRequests.get(0).getReason());
//                System.out.println(t);
//                Requests current_req = AppReq.get(cnt);
////                User  userobj = clgobj.getAllUsersMap().get(current_req.getUser().getName());
//                User userobj = current_req.getUser();
//                System.out.println("user check " + userobj);
//                current_req.setStatus("Approved");

//                main.current_user = current_req.getUser();
                if (AllRequests.get(i).equals(current_req)) {
                    DisAppReq.remove(cnt);
                    System.out.println("object found");
                    cnt--;
                    AllRequests.get(i).setStatus("Rejected");
//                    AllRequests.remove(i);
                }
            }

        }
        ArrayList<Requests> PendingRequests = new ArrayList<Requests>();
        for (int i = 0; i < AllRequests.size(); i++) {
            if (AllRequests.get(i).getStatus().equals("Pending")) {
                PendingRequests.add(AllRequests.get(i));
            }
        }
//            System.out.println("check in admin " + MainPage.clgobj.getAllUsersMap().get("ab").getRequests());
        ObservableList<Requests> display = FXCollections.observableArrayList(PendingRequests);

        
        Controller.serializeArray(AllRequests);
        College.serialize(clgobj);
        System.out.println("serialized");

    }