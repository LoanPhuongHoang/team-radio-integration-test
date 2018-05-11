package proxy

import gebtest.multiuser.User

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class UserInvocationHandler implements InvocationHandler {

	private final User user

	UserInvocationHandler(User user) {
		this.user = user
	}

	@Override
	Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		printLog(method, args)
		user.takeControl()
		method.invoke(user, args)
	}

	private printLog(Method method, Object[] args) {
		def parameter = args != null ? args.join(' ') : ''
		println "${user.username} ${method.name} $parameter"
	}

	def getUser() {
		user
	}
}
