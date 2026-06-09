package com.the703.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	private int uno;
	private String nickname;
	private String bpass;
	private String email;
	private String mobile;
	private String udate;
	private String bip;
}
/*
mysql> desc users;
+----------+-------------+------+-----+-------------------+-------------------+
| Field    | Type        | Null | Key | Default           | Extra             |
+----------+-------------+------+-----+-------------------+-------------------+
| UNO      | int         | NO   | PRI | NULL              | auto_increment    |
| NICKNAME | varchar(20) | NO   |     | NULL              |                   |
| BPASS    | varchar(20) | NO   |     | NULL              |                   |
| EMAIL    | varchar(20) | NO   |     | NULL              |                   |
| MOBILE   | varchar(20) | NO   |     | NULL              |                   |
| UDATE    | timestamp   | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| BIP      | varchar(50) | NO   |     | NULL              |                   |
+----------+-------------+------+-----+-------------------+-------------------+
7 rows in set (0.01 sec)
 */