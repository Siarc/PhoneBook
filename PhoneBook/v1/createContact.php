<?php 
 
require_once '../includes/DbOperations.php';
 
$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(
        isset($_POST['contact_name']) and
                isset($_POST['contact_number']))
        {
        //operate the data further 
 
        $db = new DbOperations(); 
 
        $result = $db->createContact($_POST['contact_name'], $_POST['contact_number']);

        if($result == 1){
            $response['error'] = false; 
            $response['message'] = "User added successfully";
        }elseif($result == 2){
            $response['error'] = true; 
            $response['message'] = "Some error occurred please try again";          
        }elseif($result == 0){
            $response['error'] = true; 
            $response['message'] = "It seems the number already exists, please choose different number";                     
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