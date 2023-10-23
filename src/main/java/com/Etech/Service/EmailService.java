package com.Etech.Service;

import com.Etech.Model.Email;

public interface EmailService {
    void send(Email email);
    void sendWithHTMLBody(Email email);
}
