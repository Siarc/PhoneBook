<?php 
 
require_once '../includes/DbOperations.php';
 
$response = array(); 
$list = array();
 
if($_SERVER['REQUEST_METHOD']=='GET'){
        $db = new DbOperations(); 

        
 
        if($list = $db->contactList()){
            $response['error'] = false; 
            
        }else{
            $response['error'] = true; 
            $response['message'] = "Invalid";          
        }
 
    }
echo json_encode($list);
