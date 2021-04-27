package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Event;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Event> getAllEvents (Nerc nerc){
		String sql = "SELECT date_event_began, date_event_finished, customers_affected"+
				" FROM poweroutages AS p, nerc AS n" +
				" WHERE p.nerc_id=n.id AND n.value = ?"
				+ "ORDER BY date_event_finished ASC";
		
		List<Event> eventList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nerc.getValue());
			ResultSet res = st.executeQuery();
			
			
			
			while (res.next()) {
				LocalDateTime ldt = res.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime ldt2 = res.getTimestamp("date_event_finished").toLocalDateTime();
				
				Duration duration = Duration.between(ldt, ldt2);
				
				double seconds = duration.getSeconds();
				double mm = (seconds / 60);
				double hh = (mm/60);
				Event e = new Event(ldt, ldt2, hh, res.getInt("customers_affected"));
				eventList.add(e);
				
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return eventList;
		
		
		
	}
}
