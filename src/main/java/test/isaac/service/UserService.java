package test.isaac.service;

import test.isaac.model.User;

public interface UserService {
	
	//查詢使用者by id
	public User getUser(Integer userId);
	
	//查詢使用者by account+password
	public User findUserByAcctPwd(String account, String password);
	
	//查詢使用者by account
	public User findUserByAcct(String account);
	
	//新增使用者
	public Integer addUser(User user);

	//設定密碼-忘記密碼
	public Integer updatePwd(String account, String password);
	
	//取得加密後密碼
	public String getmd5Pwd(String pwd);
	
	//取得新密碼
	public String getnewPwd();
	
	//寄信功能
	public String sendMail(String account);
}
