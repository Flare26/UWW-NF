//Nathan Frazier Lab 10 Client - RUNTIME
public class L10Client {

	public static void main(String[] args) {

		L10Student stu1 = new L10Student("Kannot Decide", 1010, -2.87, "Freshman");
		L10Student stu2 = new L10Student("Willis Smith", 8083, 3.8, "Junior", "Bio-Chemistry", "Sports Medicine");
		L10Student stu3 = new L10Student("Tahm Kench, River King", 6300, 975, "Senior", "Underwater Basket-Weaving", "Marine-Bio");
		L10Student stu4 = new L10Student("Jay Kole", 8374, 3.4, "Senior", "Musical Theater", "Audio Prodiction");
		L10Student stu5 = new L10Student("Robert Lazar", 1989, 4.0, "Sophomore", "Aerospace Engineering", "Chemial Engineering");
		//Place students into a class list
		L10Student [] classList = new L10Student [] {
				stu1,
				stu2,
				stu3,
				stu4,
				stu5
		};
		
		L10Instructor stormin_norman = new L10Instructor("Jeff Norman" , 4264, "Sciences" );
		L10Instructor i2 = new L10Instructor("Elon Musk", 8923, "Tesla");
		
		L10Course c1 = new L10Course("Intro to Physics", "SCI", 2001, 1 , stormin_norman, classList );
		L10Course c2 = new L10Course("Introduction to Artificial Intelligence", "COMPSCI", 332 , 2 , i2, classList);
		L10Course c3 = new L10Course("Business Management", "BIZ", 111, 4 , i2, classList);
		
		
		//END INITIALIZATION
		System.out.println("--Student objects toString() :\n");
		System.out.println(stu1.toString());
		System.out.println(stu2.toString());
		System.out.println(stu3.toString());
		System.out.println(stu4.toString());
		System.out.println(stu5.toString());
		
		System.out.println();
		System.out.println("--Instrtuctor objects toString() :\n");
		System.out.println(stormin_norman.toString());
		System.out.println(i2.toString());
		
		System.out.println();
		System.out.println("--Course objects toString() :\n");
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println(c3.toString());
		
		System.out.println("\n\t Testing class list.....");
		for ( int i = 0 ; i < c1.getClassList().length ; i ++ ) {
			L10Student [] temp = c1.getClassList();
			System.out.println(temp[i].getName());
		}

		System.out.println("\t...Finished!!!");

	}

}
