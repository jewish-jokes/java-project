package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.UserObjects;

import java.sql.Connection;
import java.util.Vector;

public interface SelectableTicket extends Requestable {
    Vector<UserObjects> executeSelect(Connection conn);
}
