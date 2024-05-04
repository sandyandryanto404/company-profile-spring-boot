package com.api.backend.utilities;

public class JsonResponse {

	private boolean Status;
	private String Message;
	private Object Data;

	public JsonResponse(boolean _status, String _message, Object _data) {
		this.Status = _status;
		this.Message = _message;
		this.Data = _data;
	}

	public boolean isStatus() {
		return this.Status;
	}

	public boolean getStatus() {
		return this.Status;
	}

	public void setStatus(boolean Status) {
		this.Status = Status;
	}

	public String getMessage() {
		return this.Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}

	public Object getData() {
		return this.Data;
	}

	public void setData(Object Data) {
		this.Data = Data;
	}
}
