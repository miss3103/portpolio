<%@page import="edu.kb.ex.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		String action = request.getParameter("action");
	
		if(action.equals("login")){
			MemberDto dto = m.getDB(v.getId());
			if(vo != null){
				if(vo.getId().equals(v.getId())){
					if(vo.getPwd().equals(v.getPwd())){
						out.println("�α��� �Ǿ����ϴ�.");
					}
					else{
						out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
					}
				}
			}
			else{
				out.println("���̵� Ʋ�Ƚ��ϴ�.");
			}
		}
		else if(action.equals("register")){
			int cnt = m.insertDB(v);
			if(cnt > 0){
				out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
			}
			else{
				out.println("ȸ�����Կ� �����Ͽ����ϴ�.");
			}
		}
	%>
</body>
</html>