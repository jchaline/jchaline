package pacmaster

class CustomerController {

    def index() {
		render "hello world"
	}
	
	def list(){
		def list = Customer.list()
		[list:list]
	}
}
