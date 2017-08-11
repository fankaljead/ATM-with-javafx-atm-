package beta1;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestAtm1 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		ATMMachine1 atm1 = new ATMMachine1();
		try {
			atm1.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
