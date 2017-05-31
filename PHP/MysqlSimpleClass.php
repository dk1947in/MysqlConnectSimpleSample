<?php

class MysqlSimpleClass {

	private $db_hostname;
	private $db_name;
	private $db_username;
	private $db_password;
	private $link;

	public function __construct() {
		$this->db_hostname = 'localhost';
		$this->db_name = 'misao';
		$this->db_username = 'shohei';
		$this->db_password = 'coin1441';

		$this->link = new mysqli($this->db_hostname, $this->db_username, $this->db_password, $this->db_name);
		if($this->link->connect_error) {
			die('Connection failed: ' . $this->link->connect_error);
		}
	}

	public function get($table_name, $where) {
		$query = 'SELECT * FROM ' . $table_name;

		if(!empty($where)) {
			$temp = [];
			foreach($where as $attr => $val) {
				$temp[] = $attr . '="' . $val . '"';
			}
			$query .= ' WHERE ' . implode(' AND ', $temp);
		}

		$result = $this->link->query($query);
		$datas = [];
		if($result->num_rows > 0) {
			while($rows = $result->fetch_assoc()) {
				$datas[] = $rows;
			}
			return $datas;
		}

		return $datas;
	}

	public function insert($table_name, $datas) {
		$keys = array_keys($datas);
		$values = array_values($datas);

		$query = 'INSERT INTO ' . $table_name;
		$query .= ' (' . implode(',', $keys) . ')';
		$query .= ' VALUES("' . implode('","', $values) . '")';

		if($this->link->query($query)) {
			return null;
		}
		else {
			return $this->link->error;
		}
	}

	public function update($table_name, $datas, $where) {
		$query = 'UPDATE ' . $table_name . ' SET ';
		foreach($datas as $attr => $val) {
			$query .= $attr . '="' . $val . '",';
		}
		$query = rtrim($query, ',');
		if(!empty($where)) {
			$query .= ' WHERE ';
		}
		$temp = [];
		foreach($where as $attr => $val) {
			$temp[] = $attr . '="' . $val . '"';
		}
		$query .= implode(' AND ', $temp);

		if($this->link->query($query)) {
			return null;
		}
		else {
			return $this->link->error;
		}
	}

	public function delete($table_name, $where) {
		$query = 'DELETE FROM ' . $table_name;
		if(!empty($where)) {
			$query .= ' WHERE ';
		}
		$temp = [];
		foreach($where as $attr => $val) {
			$temp[] = $attr . '="' . $val . '"';
		}
		$query .= implode(' AND ', $temp);

		if($this->link->query($query)) {
			return null;
		}
		else {
			return $this->link->error;
		}
	}

}

?>