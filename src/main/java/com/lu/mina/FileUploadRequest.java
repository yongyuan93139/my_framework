package com.lu.mina;

import java.io.Serializable;

public class FileUploadRequest implements Serializable
{
	private String hostname;
	private String filename;
	private byte[] fileContent;

	public String getHostname()
	{
		return hostname;
	}

	public void setHostname(String hostname)
	{
		this.hostname = hostname;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public byte[] getFileContent()
	{
		return fileContent;
	}

	public void setFileContent(byte[] fileContent)
	{
		this.fileContent = fileContent;
	}
}
