package com.multiauth.multiauthapplication.config.exemption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.ConstraintDeclarationException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExemptionError extends ConstraintDeclarationException {

    private String messageCode;
    private String errorMessage;
    private String message;

    public ExemptionError(ExemptionErrorMessageBase codeMessage) {
        this.messageCode = codeMessage.getCode();
        this.errorMessage = codeMessage.getMessage();
        this.message = codeMessage.getMessage();
    }

}
