<%@ page import="com.salmonllc.util.MessageLog,
				 java.io.*"%>
<%!
	public static int returnFile(String filename, OutputStream out)
			throws FileNotFoundException, IOException {
		InputStream in = null;
		int count = 0;
		try {

			in = new BufferedInputStream(new FileInputStream(filename));
			byte[] buf = new byte[4 * 1024];  // 4K char buffer
			int charsRead;
			while ((charsRead = in.read(buf)) != -1) {
				count += charsRead;
				out.write(buf, 0, charsRead);
			}
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
		return count;
	}
%>
<%
	File tmpDestFile = new File(request.getParameter("reportFileName"));
	MessageLog.writeInfoMessage("isCommited: " + response.isCommitted(), this);
	response.setContentType("application/msexcel");
	response.setHeader("Content-Disposition", "attachment; filename=" + "report.xls");
	response.setContentLength((int)tmpDestFile.length());
	MessageLog.writeInfoMessage("isCommited: " + response.isCommitted(), this);
	returnFile(tmpDestFile.getAbsolutePath(), response.getOutputStream());

%>