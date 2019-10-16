
public class Lab10inclass {
	public static void main( String [] args ) { 
		Movie m1 = new Movie( "2001: A Space Odyssey", "Stanley Kubrick", 1968, 9.9d );
		Movie m2 = new Movie( "Interstellar" , "Christopher Nolan" , 2014, 9.74d );
		
		Theatre t1 = new Theatre("Whitewater Cinema" , m1 , 7.50d , 35 );
		Theatre t2 = new Theatre("McHenry Drive-in" , m2 , 11.00d , 50);
		
		for ( int i = 0 ; i < 1000 ; i++) {
			if ( t1.canSellTicket() )
				t1.sellTicket();
		}
		

		for ( int i = 0 ; i < 1000 ; i++) {
			if ( t2.canSellTicket() )
				t2.sellTicket();
		}
		
		System.out.printf("%s sold %d / %d for a total income of $%.2f",
			t1.getName() ,
			t1.getTicketsSold() ,
			t1.getSeatCapacity() ,
			t1.getTicketIncome()
			);
		
		System.out.println();
		
		System.out.printf("%s sold %d / %d for a total income of $%.2f",
				t2.getName() ,
				t2.getTicketsSold() ,
				t2.getSeatCapacity() ,
				t2.getTicketIncome()
				);
		
		//Static variables are SHARED
		System.out.printf( "\n\nTotal tickets sold:\t%d" , Theatre.totalTicketsSold );
		System.out.printf( "\nTotal ticket income:\t$ %.2f" , Theatre.totalTicketIncome );
		System.out.println();
		System.out.println( t1 );
		System.out.println( t2 );
		System.out.println( m1 );
		System.out.println( m2 );
	}
   
}
