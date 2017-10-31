<?php 
 
require_once '../includes/DbOperations.php';
 
$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(
        isset($_POST['contact_name']) and
        isset($_POST['contact_number']) and
        isset($_POST['contact_id']))
        {
        //operate the data further 
 
        $db = new DbOperations(); 
 
        $result = $db->updateContact($_POST['contact_name'], $_POST['contact_number'], $_POST['contact_id']);

        if($result == true){
            $response['error'] = false; 
            $response['message'] = "User Updated successfully";
        }else if($result == false){
            $response['error'] = true; 
            $response['message'] = "Some error occurred please try again";          
        }
 
    }else{
        $response['error'] = true; 
        $response['message'] = "Required fields are missing";
    }
}else{
    $response['error'] = true; 
    $response['message'] = "Invalid Request";
}
 
echo json_encode($response);