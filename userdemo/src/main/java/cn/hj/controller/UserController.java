package cn.hj.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.hj.entity.User;
import cn.hj.service.UserService;
@Controller
@RequestMapping(value = "/user")
@SessionAttributes("mysession")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	private int width = 90;// 定义图片的width
    private int height = 40;// 定义图片的height
    private int codeCount = 4;// 定义图片上显示验证码的个数
    private int xx = 15;
    private int fontHeight = 30;
    private int codeY = 35;
    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    @RequestMapping("/code")
    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);
        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);
        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 40; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length-1)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        System.out.print(randomCode);
        session.setAttribute("code", randomCode.toString());
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }
	
	
    @ResponseBody
    @RequestMapping("/usercheck.do")
    public void checkUser(User user,HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//获取前台传来的数据
    	System.err.println("user====="+user.toString());
    	List<User> list = new ArrayList<User>();
    	list = userService.findUserByName(user);
    	for (User user1 : list) {
			System.err.println("user1--------"+user1.toString());
		}
    	//响应前台
    	response.setCharacterEncoding("utf-8");
    	//response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	String message = "";
    	if(list != null && !(list.isEmpty())){
    		if(list.get(0).getUsername().equals(user.getUsername())) {
    			 //out.println("<font color='red'>该帐号已经存在，请重新输入!</font>");
        		message = "用户存在";
    		}else{
    	         //out.println("<font color='green'>恭喜您，该帐号可以使用!</font>");
    			message = "用户不存在";
    		}
    	}
    	out.print(message);
    	//out.flush();//刷新流
    	 out.close();//关闭流
    }
	
	

	@RequestMapping(value = "reg.do", method = RequestMethod.GET)
	public String reg() {
		return "reg";
	}

//	@RequestMapping(value = "/addUser.do")
//	public String addUser(User user) {
//		userService.add(user);
//		return "success";
//
//	}
	
	@RequestMapping(value = "/addUser.do")
	public String addUser() {
		
		return "adduser";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	 public String userLogin(
			 String code ,HttpServletRequest request,HttpServletResponse response, User user ,Model model) {
		System.err.println("user==============================="+user.toString());  
		System.err.println("code==============================="+code);
		String message = "";
		
		String username = user.getUsername().trim();
		String password = user.getPassword().trim();
		if(username.isEmpty() || password.isEmpty()) {
			message = "用户名或者密码不能为空";
			model.addAttribute("message",message);
			return "login";
		}
		List<User> list = new ArrayList<User>();
		list = userService.findUserByName(user);
		
		for (User user2 : list) {
			System.err.println("user2--------"+user2.toString());
		}
		
		if(list != null && !(list.isEmpty())) {
			if(!(list.get(0).getPassword().equals(password))) {
				message = "密码错误!";
				model.addAttribute("message",message);
				return "login";
			}
		}
		
		//验证验证码
		String rs =(String)request.getSession(true).getAttribute("code");
		System.err.println("rs======="+rs);
		if(!code.equals(rs)) {
			message ="验证码错误!";
			model.addAttribute("message",message );
			return "login";
		}
		model.addAttribute("user", list.get(0));
		//model.addAttribute("message",message );
		return "success";
	    }
	
	@RequestMapping("/list")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        System.out.println(users);
        return "list";
    }
	
	@SuppressWarnings("finally")
	@RequestMapping("delete")
		public String delete(HttpServletRequest request,String InfoMessage){
		
				String id = request.getParameter("id");
				String str = null;
				if(!(id.isEmpty())) {
					str = userService.deleteUser(Integer.parseInt(id));
					System.err.println("------"+str);
				}
				request.setAttribute("InfoMessage", str);
				return "delete";
		}
	private String string;
	@RequestMapping("edit")
	public String edit(HttpServletRequest request) {
		string = request.getParameter("id");
		System.err.println("id=------"+string);
		return "edit";
	}
	
//	@RequestMapping("update")
//	public ModelAndView update(HttpServletRequest request,ModelAndView model) {
//		String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String status = request.getParameter("status");
//        System.err.println(username+"----"+password+"----"+email);
//        System.err.println("id=------"+string);
//		User user = new User();
//		user.setId(Integer.parseInt(string));
//		user.setUsername(username);
//		user.setPassword(password);
//		user.setEmail(email);
//		if(status.equals("true")) {
//			user.setStatus(true);
//		}else {
//			user.setStatus(false);
//		}
//		
//		System.err.println(user.toString());
//		int flag = 0;
//		List<User> users = new ArrayList<User>();
//		if(user != null) {
//			flag = userService.updateByPrimaryKeySelective(user);
//		}
//		if(flag > 0) {
//			users = userService.getAllUser();
//			model.addObject("list", users);
//		}else {
//			model.addObject("index", users);
//		}
//		return model;
//	}
	
	@RequestMapping("update")
	public String update(HttpServletRequest request,Model model) {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String status = request.getParameter("status");
        System.err.println(username+"----"+password+"----"+email);
        System.err.println("id=------"+string);
		User user = new User();
		user.setId(Integer.parseInt(string));
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		if(status.equals("true")) {
			user.setStatus(true);
		}else {
			user.setStatus(false);
		}	
		System.err.println(user.toString());
		int flag = 0;
		List<User> users = new ArrayList<User>();
		if(user != null) {
			flag = userService.updateByPrimaryKeySelective(user);
		}
		if(flag > 0) {
			users = userService.getAllUser();			
		    model.addAttribute("users", users);
			return "list";
		}else {			
			return "index";
		}
	}


}
