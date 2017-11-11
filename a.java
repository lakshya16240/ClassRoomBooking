    @FXML
    void ApproveRequests(ActionEvent event) throws IOException, ClassNotFoundException {
        System.out.println("Approve request called");
        ObservableList<Requests> ApprovedRequests = FXCollections.observableArrayList();;
        ApprovedRequests = requestTable.getSelectionModel().getSelectedItems();
//        System.out.println("req = " + ApprovedRequests);
        ArrayList<Requests> AllRequests = new ArrayList<Requests>();
        AllRequests = Controller.deserializeArray();
        int cnt = ApprovedRequests.size() - 1;
        System.out.println("approved requests = " + ApprovedRequests);
        System.out.println("requests = " + AllRequests);
        ArrayList<Requests> AppReq = new ArrayList<Requests>();

        for (int i = 0; i < ApprovedRequests.size(); i++) {
            AppReq.add(ApprovedRequests.get(i));
        }

        while (cnt >= 0) {
            Requests current_req = AppReq.get(cnt);
//                User  userobj = clgobj.getAllUsersMap().get(current_req.getUser().getName());
            User userobj = clgobj.getAllUsersMap().get(current_req.getUser().getEmailId());
            System.out.println("user check " + userobj);

            System.out.println("All requests on pressing Approved before check: " + userobj.getRequests());

            for (int i = 0; i < userobj.getRequests().size(); i++) {
                if (userobj.getRequests().get(i).equals(current_req)) {
                    userobj.getRequests().get(i).setStatus("Approved");
                }
            }

            clgobj.getAllUsersMap().put(current_req.getUser().getEmailId(), userobj);
           rent_req.getStatus());
//           current_req.setStatus("Approved");
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
                    AppReq.remove(cnt);
                    System.out.println("object found");
                    cnt--;
                    AllRequests.get(i).setStatus("Approved");
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
//            System.out.println("check in admin " + MainPage.clgobj.getAllUsersMap().get("ab").getRequests());
//            ObservableList<Requests> display = FXCollections.observableArrayList(AllRequests);
        Controller.serializeArray(AllRequests);
        PopulateTable();
        College.serialize(clgobj);

        System.out.println("serialized");