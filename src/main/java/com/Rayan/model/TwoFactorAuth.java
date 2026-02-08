package com.Rayan.model;

import com.Rayan.domain.VerificationType;

public class TwoFactorAuth {

	private boolean inEnabled=false;
	
	private VerificationType sendTo;

	public TwoFactorAuth(boolean inEnabled, VerificationType sendTo) {
		super();
		this.inEnabled = inEnabled;
		this.sendTo = sendTo;
	}

	public TwoFactorAuth() {
		super();
	}

	public boolean isInEnabled() {
		return inEnabled;
	}

	public void setInEnabled(boolean inEnabled) {
		this.inEnabled = inEnabled;
	}

	public VerificationType getSendTo() {
		return sendTo;
	}

	public void setSendTo(VerificationType sendTo) {
		this.sendTo = sendTo;
	}
	
	
}
