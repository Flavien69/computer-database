package com.flavien.dao.instance;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Utils {

		public static LocalDateTime getLocalDate(Timestamp timestamp){
			if(timestamp!= null)
				return timestamp.toLocalDateTime();
			else
				return null;
		}
}
