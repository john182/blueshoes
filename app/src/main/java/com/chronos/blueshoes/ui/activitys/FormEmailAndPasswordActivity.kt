package com.chronos.blueshoes.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import com.blankj.utilcode.util.KeyboardUtils
import com.chronos.blueshoes.R
import kotlinx.android.synthetic.main.text_view_privacy_policy_login.*

abstract class FormEmailAndPasswordActivity :
    FormActivity(),
    KeyboardUtils.OnSoftInputChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
       * Com a API KeyboardUtils conseguimos de maneira
       * simples obter o status atual do teclado virtual (aberto /
       * fechado) e assim prosseguir com algoritmos de ajuste de
       * layout.
       * */
        KeyboardUtils.registerSoftInputChangedListener(this, this)

    }

    override fun onDestroy() {
        KeyboardUtils.unregisterSoftInputChangedListener(this)
        super.onDestroy()
    }


    fun callPrivacyPolicyFragment(view: View) {
        val intent = Intent(
            this,
            MainActivity::class.java
        )

        /*
         * Para saber qual fragmento abrir quando a
         * MainActivity voltar ao foreground.
         * */
        intent.putExtra(
            MainActivity.FRAGMENT_ID,
            R.id.item_privacy_policy
        )

        /*
         * Removendo da pilha de atividades a primeira
         * MainActivity aberta (e a LoginActivity), para
         * deixar somente a nova MainActivity com uma nova
         * configuração de fragmento aberto.
         * */
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        startActivity(intent)
    }

    override fun onSoftInputChanged(height: Int) {

        if (isAbleToCallChangePrivacyPolicyConstraints()) {
            changePrivacyPolicyConstraints(
                KeyboardUtils.isSoftInputVisible(this)
            )
        }
    }

    open fun isAbleToCallChangePrivacyPolicyConstraints() = true


    private fun changePrivacyPolicyConstraints(
        isKeyBoardOpened: Boolean
    ) {

        val privacyId = tv_privacy_policy.id
        val parent = tv_privacy_policy.parent as ConstraintLayout
        val constraintSet = ConstraintSet()

        /*
         * Definindo a largura e a altura da View em
         * mudança de constraints, caso contrário ela
         * fica com largura e altura em 0dp.
         * */
        constraintSet.constrainWidth(
            privacyId,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        constraintSet.constrainHeight(
            privacyId,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        /*
         * Centralizando a View horizontalmente no
         * ConstraintLayout.
         * */
        constraintSet.centerHorizontally(
            privacyId,
            ConstraintLayout.LayoutParams.PARENT_ID
        )

        if (isConstraintToSiblingView(isKeyBoardOpened)) {

            setConstraintsRelativeToSiblingView(constraintSet, privacyId)
        } else {
            /*
             * Se o teclado virtual estiver fechado, então
             * mude a configuração da View alvo
             * (tv_privacy_policy) para ficar vinculada ao
             * fundo do ConstraintLayout ancestral.
             * */
            constraintSet.connect(
                privacyId,
                ConstraintLayout.LayoutParams.BOTTOM,
                ConstraintLayout.LayoutParams.PARENT_ID,
                ConstraintLayout.LayoutParams.BOTTOM
            )
        }

        constraintSet.applyTo(parent)
    }

    /*
    * Método único.
    *
    **/
    abstract fun isConstraintToSiblingView(isKeyBoardOpened: Boolean): Boolean

    /*
     * Método único.
     * */
    abstract fun setConstraintsRelativeToSiblingView(
        constraintSet: ConstraintSet,
        privacyId: Int
    )
}