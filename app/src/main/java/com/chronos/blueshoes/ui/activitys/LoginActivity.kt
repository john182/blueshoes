package com.chronos.blueshoes.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import com.blankj.utilcode.util.ScreenUtils
import com.chronos.blueshoes.R
import com.chronos.blueshoes.util.ext.isValidEmail
import com.chronos.blueshoes.util.ext.isValidPassword
import com.chronos.blueshoes.util.ext.validate
import kotlinx.android.synthetic.main.content_form.*
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity :
    FormEmailAndPasswordActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
         * Colocando a View de um arquivo XML como View filha
         * do item indicado no terceiro argumento.
         * */
        View.inflate(
            this,
            R.layout.content_login,
            fl_form
        )



        /*
         * Colocando configuração de validação de campo de email
         * para enquanto o usuário informa o conteúdo deste campo.
         * */
        et_email.validate(
            {
                et_email.text.toString().isValidEmail()
            },
            getString(R.string.invalid_email)
        )

        /*
         * Colocando configuração de validação de campo de senha
         * para enquanto o usuário informa o conteúdo deste campo.
         * */
        et_password.validate(
            {
                et_password.text.toString().isValidPassword()
            },
            getString(R.string.invalid_password)
        )

        et_password.setOnEditorActionListener(this)
    }



    override fun mainAction(view: View?) {
        blockFields(true)
        isMainButtonSending(true)
        showProxy(true)
        backEndFakeDelay(false, getString(R.string.invalid_login))
    }

    override fun blockFields(status: Boolean) {
        et_email.isEnabled = !status
        et_password.isEnabled = !status
        bt_login.isEnabled = !status
    }

    override fun isMainButtonSending(status: Boolean) {
        bt_login.text =
            if (status)
                getString(R.string.sign_in_going)
            else
                getString(R.string.sign_in)
    }

    override fun isAbleToCallChangePrivacyPolicyConstraints() = ScreenUtils.isPortrait()


    override fun isConstraintToSiblingView(isKeyBoardOpened: Boolean) = isKeyBoardOpened


    override fun setConstraintsRelativeToSiblingView(
        constraintSet: ConstraintSet,
        privacyId: Int
    ) {

        /*
         * Se o teclado virtual estiver aberto, então
         * mude a configuração da View alvo
         * (tv_privacy_policy) para ficar vinculada a
         * View acima dela (tv_sign_up).
         * */
        constraintSet.connect(
            privacyId,
            ConstraintLayout.LayoutParams.TOP,
            tv_sign_up.id,
            ConstraintLayout.LayoutParams.BOTTOM,
            (12 * ScreenUtils.getScreenDensity()).toInt()
        )
    }


    /* Listeners de clique */
    fun callForgotPasswordActivity(view: View) {
        val intent = Intent(
            this,
            ForgotPasswordActivity::class.java
        )

        startActivity(intent)
    }

    fun callSignUpActivity(view: View) {
        val intent = Intent(
            this,
            SignUpActivity::class.java
        )

        startActivity(intent)
    }

}