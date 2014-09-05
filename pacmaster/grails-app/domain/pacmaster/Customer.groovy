package pacmaster

class Customer {

	String firstName, lastName, email
	Date arrival, nextPac, lastPac
	
    static constraints = {
		firstName()
		lastName()
		email()
		arrival()
		lastPac()
		nextPac()
		
    }
}
