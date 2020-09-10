package app.moebius.domain.entity.security

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table


/**
 *  Obs: Are manipulated by the identity
 */
@Entity
@Table(name = "security_methods")
data class SecurityMethods(
        val securityMethodsUUID: UUID,
        val identityVerification: IdentityVerification? = null,
        val twoFA: TwoFA? = null,
        val antiPishingCode: AntiPishingCode? = null,
        val keepSessionDaily: Boolean = false
)

@Entity
@Table(name = "two_factor_authentication")
data class TwoFA(
        val twoFAUUID: UUID,
        val enable: Boolean = false,
        val googleAuthentication: GoogleAuthenticaton? = null,
        val smsAuthentication: SMSAuthentication? = null,
        val emailVerification: EmailVerification? = null
)

@Entity
@Table(name = "google_authentication")
data class GoogleAuthenticaton(
        val googleAuthenticationUUID: UUID,
        val verificationCode: Int
)

@Entity
@Table(name = "sms_authentication")
data class SMSAuthentication(
        val smsAuthenticationUUID: UUID,
        val verificationCode: Int
)

@Entity
@Table(name = "email_verification")
data class EmailVerification(
        val emailVerificationUUID: UUID,
        val isEmailVerified: Boolean,
        val emailVerificationToken: String,
)

@Entity
@Table(name = "anti_pishing_code")
data class AntiPishingCode(
        val antiPishingCodeUUID: UUID,
        val enable: Boolean,
        val code: String? = null
)