import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateParser {

	private ZoneId zoneId;
	private final long time;

	public DateParser(long time) {
		this.time = time;
		this.zoneId = ZoneId.systemDefault();
	}

	public DateParser(long time, ZoneId zoneId) {
		this.time = time;
		this.zoneId = zoneId;
	}

	public void setZoneId() {
		this.zoneId = ZoneId.systemDefault();
	}

	public static DateParser fromDate(java.util.Date src) {
		long time = src.getTime();
		return new DateParser(time);
	}

	public static DateParser fromSqlDate(java.sql.Date src) {
		long time = src.getTime();
		return new DateParser(time);
	}

	public static DateParser fromTimestamp(Timestamp src) {
		long time = src.getTime();
		return new DateParser(time);
	}

	public static DateParser fromLocalDateTime(LocalDateTime src, ZoneId zoneId) {
		java.util.Date date = java.util.Date.from(src.atZone(zoneId).toInstant());
		long time = date.getTime();
		return new DateParser(time, zoneId);
	}
	
	public static DateParser fromLocalDate(LocalDate src, ZoneId zoneId) {
		java.sql.Date sqlDate = java.sql.Date.valueOf(src);
		long time = sqlDate.getTime();
		return new DateParser(time, zoneId);
	}

	public java.util.Date toDate() {
		return new java.util.Date(time);
	}

	public java.sql.Date toSqlDate() {
		return new java.sql.Date(time);
	}

	public Timestamp toTimestamp() {
		return new Timestamp(time);
	}

	public LocalDateTime toLocalDateTime() {
		java.util.Date date = new java.util.Date(time);
		return date.toInstant().atZone(zoneId).toLocalDateTime();
	}
	
	public LocalDate toLocalDate() {
		java.util.Date date = new java.util.Date(time);
		return date.toInstant().atZone(zoneId).toLocalDate();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("time: ").append(time).append(", ");
		sb.append("zoneId: ").append(zoneId.toString()).append(", ");
		sb.append("date: ").append(toDate().toString()).append(", ");
		sb.append("sqlDate: ").append(toSqlDate().toString()).append(", ");
		sb.append("timestamp: ").append(toTimestamp().toString()).append(", ");
		sb.append("localDateTime: ").append(toLocalDateTime().toString()).append(", ");
		sb.append("localDate: ").append(toLocalDate().toString());
		return sb.toString();
	}

    @Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (this.getClass() != obj.getClass())
			return false;
		DateParser dp = (DateParser) obj;
		return this.time == dp.time && this.zoneId.equals(dp.zoneId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(time, zoneId);
	}
}
