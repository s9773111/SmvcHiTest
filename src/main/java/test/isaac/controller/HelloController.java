package test.isaac.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import test.isaac.model.User;
import test.isaac.service.UserService;

@Controller
public class HelloController {
	
	@Autowired
	UserService us;
	
	
	//首頁
	@RequestMapping(value="/home")
	public ModelAndView index() {
		System.out.println("登入首頁Controller");
		ModelAndView mv = new ModelAndView("home");
	    //User user = us.getUser(1);
	    //mv.addObject("result", user);
	    return mv;
	  }
	
	//個人資訊頁面：登入後顯示個人資料
	@RequestMapping(value= {"/login"}, method=RequestMethod.POST)
	public ModelAndView login(
			@RequestParam(value="account", required=false) String account,
			@RequestParam(value="password", required=false)String password
			) {
		System.out.println("登入前檢查是否為會員");
		System.out.println("帳號:"+account);
		System.out.println("密碼:"+password);
		ModelAndView mv;
		User result = null;
		result = us.findUserByAcctPwd(account, us.getmd5Pwd(password));
		System.out.println("查詢是否人在資料庫中："+result);
		if(result != null) {
			System.out.println("使用者帳號是："+result.getUacct());
			return mv = new ModelAndView("userInfo", "result", result);
		}else {
			return mv = new ModelAndView("home", "result", "登入失敗請重新輸入");
		}
		//return null;
	}
	
	//註冊頁面
	@RequestMapping(value= {"/register"}, method=RequestMethod.GET)
	public ModelAndView register() {
		System.out.println("註冊頁面，Controller");
		ModelAndView mv = new ModelAndView("register");
		return mv;
	}
	
	//忘記密碼頁面-GET方法
	@RequestMapping(value= {"/password"}, method=RequestMethod.GET)
	public ModelAndView password() {
		System.out.println("到達忘記密碼頁面");
		ModelAndView mv =  new ModelAndView("password");
		return mv;
	}
	
	//忘記密碼頁面-POST方法
	@RequestMapping(value= {"/password"}, method=RequestMethod.POST)
	public ModelAndView passwordUpdate(@RequestParam(value="account",required=false)String account) {
		System.out.println("查詢帳號的信箱(POST)");
		System.out.println("account:"+account);
		ModelAndView mv ;
		
		String result = us.sendMail(account);
		return mv = new ModelAndView("home", "result", result);
	}
	
	//註冊成為會員-表單POST
	@RequestMapping(value= {"/regmember"}, method=RequestMethod.POST)
	public ModelAndView regmemeber(
			@RequestParam(value="Uaccount", required=false) String Uaccount,
			@RequestParam(value="Upassword", required=false) String Upassword,
			@RequestParam(value="Uname", required=false) String Uname,
			@RequestParam(value="Uemail", required=false) String Uemail,
			@RequestParam(value="Ubday", required=false) Date Ubday,
			@RequestParam(value="Usex", required=false) int Usex
			){
		System.out.println("註冊會員 審查資料！！");
		ModelAndView mv;
		String resulttxt=null;
		
		//判斷帳號是否註冊過 若沒有才能插入資料庫中
		User user = null; 
		user = us.findUserByAcct(Uaccount);
		if(user != null) {
			//帳號有在資料庫中
			System.out.println("帳號有在資料庫");
			return mv = new ModelAndView("register", "result", "帳號重複");
		}else {
			System.out.println("帳號沒有在資料庫中");
			user = new User(Uname, Ubday, Usex, Uemail, us.getmd5Pwd(Upassword), Uaccount);
			try {
				Integer result =  us.addUser(user);
				System.out.println("新增會員結果:"+result);
				resulttxt = "成功註冊。";
			}catch(Exception e) {
				e.printStackTrace();
				resulttxt = "註冊失敗!";
			}
		}
		return mv = new ModelAndView("home", "result", resulttxt);
	}
	
	//註冊時 檢查帳號是否有重複
	@RequestMapping(value= {"/regmember"}, method=RequestMethod.GET)
	public ModelAndView checkAccount(@RequestParam(value="account", required=false)String account, HttpServletResponse response) {
		System.out.println("檢查帳號是否重複...");
		ModelAndView mv = new ModelAndView("register");
		System.out.println("參數帳號值："+account);
		
		response.setContentType("text/html;charset=utf-8");
		
		//判斷是否有會員
		User user = null;
		user = us.findUserByAcct(account);
		
		if(user != null) {
			System.out.println("資料庫有帳號："+user.getUacct());
			try {
				response.getWriter().write("帳號已存在不可註冊");
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//mv.addObject("result", "帳號可以註冊");
		}else {
			System.out.println("無帳號:"+user);
			try {
				response.getWriter().write("帳號可以使用");
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//mv.addObject("result", "帳號已存在不可註冊");
		}
		return mv;
	}
}
