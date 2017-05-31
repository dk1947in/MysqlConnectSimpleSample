<?php

include('MysqlSimpleClass.php');
$sql = new MysqlSimpleClass();
$table_name = 'user_info';

if(isset($_POST['SELECT'])) {
	$u_id = $_POST['u_id'];

	if($u_id == '' || $u_id == null) {
		$where = [];
	}
	else {
		$where = ['u_id' => $u_id];
	}

	$datas['data'] = $sql->get($table_name, $where);

	echo json_encode($datas);
}

else if(isset($_POST['INSERT'])) {
	$user_info = [
		'u_name' => $_POST['u_name'],
		'u_email' => $_POST['u_email'],
		'u_phone' => $_POST['u_phone'],
		'u_pass' => $_POST['u_pass']
	];

	if(empty(array_values($user_info))) {
		$error = 'Not send data';
	}

	if(is_null($error)) {
		$error = $sql->insert($table_name, $user_info);
	}
}

else if(isset($_POST['UPDATE'])) {
	$where = ['u_id' => $_POST['u_id']];

	$user_info = [
		'u_name' => $_POST['u_name'],
		'u_email' => $_POST['u_email'],
		'u_phone' => $_POST['u_phone']
	];
	foreach($user_info as $key => $val) {
		if($val !== '') {
			$updated[$key] = $val;
		}
	}

	if(empty(array_values($where)) || empty(array_values($user_info))) {
		$error = 'Not send data';
	}

	if(is_null($error)) {
		$error = $sql->update($table_name, $updated, $where);
	}
}

else if(isset($_POST['DELETE'])) {
	$where = ['u_id' => $_POST['u_id']];

	if(empty(array_values($where))) {
		$error = 'Not send data';
	}

	if(is_null($error)) {
		$error = $sql->delete($table_name, $where);
	}
}

?>