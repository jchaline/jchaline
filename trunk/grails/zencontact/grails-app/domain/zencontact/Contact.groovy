package zencontact

class Contact {
	String nom, prenom, email
	Date dateNaissance

    static constraints = {
		prenom(blank:false)
		nom(blank:false)
		dateNaissance(max:new Date(),nullable:true)
		email(email:true, nullable:true,blank:true,unique:true) 
    }
}
