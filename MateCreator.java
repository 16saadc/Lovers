import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;


public class MateCreator {

	public static void objMapper() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			PotentialMate mate = mapper.readValue("mateList.json", PotentialMate.class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}