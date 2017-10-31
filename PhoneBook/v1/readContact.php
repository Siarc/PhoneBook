<?php 
 
require_once '../includes/DbOperations.php';
 
$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['contact_number'])){
        $db = new DbOperations(); 
 
        if($contact = $db->readContactInfo($_POST['contact_number'])){
            $response['error'] = false; 
            $response['id'] = $contact['id'];
            $response['contact_name'] = $contact['contact_name'];
            $response['contact_number'] = $contact['contact_number'];
        }else{
            $response['error'] = true; 
            $response['message'] = "Invalid";          
        }
 
    }else{
        $response['error'] = true; 
        $response['message'] = "Required fields are missing";
    }
}
 
echo json_encode($response);