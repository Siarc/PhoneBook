<?php 
 
    class DbOperations{
 
        private $con; 
 
        function __construct(){
 
            require_once dirname(__FILE__).'/DbConnect.php';
 
            $db = new DbConnect();
 
            $this->con = $db->connect();
 
        }
 
        //CRUD -> C -> CREATE
 
        public function createContact($contact_name, $contact_number){
            if($this->isNumberExist($contact_number)){
                return 0;
            }else{
                $stmt = $this->con->prepare("INSERT INTO `contacts` (`id`, `contact_name`, `contact_number`) VALUES (NULL, ?, ?);");
                $stmt->bind_param("ss",$contact_name,$contact_number);
 
                if($stmt->execute()){
                    return 1; 
                }else{
                    return 2; 
                }
            }
        }
 
        private function isNumberExist($contact_number){
            $stmt = $this->con->prepare("SELECT id FROM contacts WHERE contact_number = ?");
            $stmt->bind_param("s",$contact_number);
            $stmt->execute(); 
            $stmt->store_result();
            return $stmt->num_rows > 0; 
        }

        //CRUD -> R -> READ

        public function readContactInfo($contact_number){
            $stmt = $this->con->prepare("SELECT * FROM contacts WHERE contact_number = ?");
            $stmt->bind_param("s",$contact_number);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();
        }

        //READ AND STORE ALL THE DATA IN AN ARRAY OR LIST

        public function contactList(){
            $stmt = $this->con->prepare("SELECT id, contact_name, contact_number FROM contacts");
            $stmt->execute();
            $stmt->bind_result($id,$contact_name,$contact_number);
            $list = array();
            while ($stmt->fetch()) {
                $temp = array();
                $temp['id'] = $id;
                $temp['contact_name'] = $contact_name;
                $temp['contact_number'] = $contact_number;

                array_push($list, $temp);
            }

            return $list;
        }

        //CRUD -> U -> UPDATE

        public function updateContact($contact_name, $contact_number,$contact_id){
            if($this->isIdExist($contact_id)){
                $stmt = $this->con->prepare("UPDATE contacts SET contact_name = ?, contact_number = ? WHERE id = ?");
                $stmt->bind_param("sss",$contact_name,$contact_number,$contact_id);
                $stmt->execute();
                return true;
            }else{
                return false;
            }
            
        }

         private function isIdExist($contact_id){
            $stmt = $this->con->prepare("SELECT id FROM contacts WHERE id = ?");
            $stmt->bind_param("s",$contact_id);
            $stmt->execute(); 
            $stmt->store_result();
            return $stmt->num_rows > 0; 
        }

        //CRUD -> D -> DELETE

        public function deleteContact($contact_id){

            $stmt = $this->con->prepare("DELETE FROM contacts WHERE id = ?");
            $stmt->bind_param("s",$contact_id);
            $stmt->execute();
            return 0;
        
            
        }

    }