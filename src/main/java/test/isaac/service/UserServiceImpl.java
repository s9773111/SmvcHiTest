package test.isaac.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import test.isaac.dao.UserDao;
import test.isaac.mail.JavaMail;
import test.isaac.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional // 交易管理註釋
	@Override
	public User getUser(Integer userId) {
		return userDao.getUser(userId);
	}

	@Transactional
	@Override
	public User findUserByAcctPwd(String account, String password) {
		return userDao.findUserByAcctPwd(account, password);
	}

	@Transactional
	@Override
	public User findUserByAcct(String account) {
		return userDao.findUserByAcct(account);
	}

	@Transactional
	@Override
	public Integer addUser(User user) {
		return userDao.addUser(user);
	}

	@Transactional
	@Override
	public Integer updatePwd(String account, String password) {
		return userDao.updatePwd(account, password);
	}

	// 使用md5加密密碼
	@Override
	public String getmd5Pwd(String pwd) {
		String md5Str = DigestUtils.md5DigestAsHex(pwd.getBytes());
		return md5Str;
	}

	// 產生新密碼
	@Override
	public String getnewPwd() {
		Random ran = new Random();
		String pwd = "";
		for (int i = 1; i < 9; i++) {
			int newpwd = ran.ints(1, 9 + 1).findFirst().getAsInt();
			pwd += newpwd;
		}
		System.out.println("新密碼：" + pwd);
		return pwd;
	}

	// 寄信功能
	@Override
	public String sendMail(String account) {
		System.out.println("開始寄信 帳號：" + account);
		// init
		User user = null;
		String mail = null;
		String newpwd = null;
		int updatepwd = 0;

		// 先取得帳號 帳號獲得信箱
		user = findUserByAcct(account);
		if (user != null) {
			// 帳號有在資料庫中-開始寄信
			mail = user.getUmail();
			System.out.println("獲得信箱：" + mail);

			// 產生一組新密碼(md5) 並寫入資料庫
			newpwd = getnewPwd();
			updatepwd = updatePwd(account, getmd5Pwd(newpwd));
			System.out.println("更改密碼成功");

			// 寫入資料庫成功，信件寄到使用者信箱
			if (updatepwd > 0) {
				// 更改密碼成功
				JavaMail jmail = new JavaMail(mail, newpwd);
				jmail.SendMail();
				System.out.println("完成寄送密碼");
				return "寄信成功";
			} else {
				// 更改密碼失敗
				System.out.println("寄送密碼失敗");
				return "寄信失敗";
			}
		} else {
			// 帳號不在資料庫中-無法寄信
			return "查無此使用者";
		}

//		//先取得帳號 帳號獲得信箱
//		user = findUserByAcct(account);
//		mail = user.getUmail();
//		System.out.println("獲得信箱："+mail);
//		
//		//產生一組新密碼(md5) 並寫入資料庫
//		newpwd = getnewPwd();
//		updatepwd = updatePwd(account, getmd5Pwd(newpwd));
//		
//		//信件寄到使用者信箱
//		if(updatepwd > 0) {
//			//更改密碼成功
//			JavaMail jmail = new JavaMail(mail, newpwd);
//			jmail.SendMail();
//			return "寄信成功"; 
//		}else {
//			//更改密碼失敗
//			return  "寄信失敗";
//		}
	}
}
