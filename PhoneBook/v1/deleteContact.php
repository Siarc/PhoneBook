<?php 
 
require_once '../includes/DbOperations.php';
 
$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['contact_id'])){
        $db = new DbOperations(); 

        $result = $db->deleteContact($_POST['contact_id']);
 
        if($result == 0){
            $response['error'] = false; 
            $response['message'] = "Contact info deleted";
        }else if($result == 1){
            $response['error'] = true; 
            $response['message'] = "Number Does not Exist";          
        }
 
    }else{
        $response['error'] = true; 
        $response['message'] = "Required fields are missing";
    }
}
 
echo json_encode($response);