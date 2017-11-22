package com.shsxt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


@Controller
@RequestMapping("/media")
public class PhotoController
{
	private static final String BASEPATH = "E:\\tianwei\\imageTest\\upload";
	private static final String CONTENT_TYPE = "image/jpg";

	@RequestMapping("/userPhoto/{photo}")
	public void media(@PathVariable("photo") String photoName, HttpServletResponse response) throws IOException
	{
		String result = photoName.replaceAll("_", "/");
		String decodingName = new String(Base64.decode(result));
		String path = BASEPATH + File.separator + decodingName;
		System.out.println("path:" + path);
		File file = new File(path);
		InputStream inputStream = new FileInputStream(file);
		byte[] b = new byte[inputStream.available()];
		inputStream.read(b);
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(b);
		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}
}
