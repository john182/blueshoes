package com.chronos.blueshoes.ui.fragments


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chronos.blueshoes.R
import com.chronos.blueshoes.ui.activitys.MainActivity
import kotlinx.android.synthetic.main.fragment_about.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AboutFragment : Fragment(), View.OnClickListener {

    companion object {
        const val ID = 22568
        const val TITLE_ID = R.string.about_frag_title
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    /*
   * Para que não tenhamos problemas de Views ainda não
   * carregadas em tela para possível acesso em código
   * dinâmico, as Views do layout de AboutFragment estão
   * sendo acessadas somente em método posterior ao
   * método onCreateView(), no caso o método onActivityCreated(),
   * digo, Views sendo acessadas via sintaxe
   * kotlin-android-extensions.
   * */

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        iv_instagram.setOnClickListener(this)
        tv_instagram.setOnClickListener(this)

        iv_facebook.setOnClickListener(this)
        tv_facebook.setOnClickListener(this)

        iv_twitter.setOnClickListener(this)
        tv_twitter.setOnClickListener(this)

        iv_youtube.setOnClickListener(this)
        tv_youtube.setOnClickListener(this)

        iv_linkedin.setOnClickListener(this)
        tv_linkedin.setOnClickListener(this)
    }

    /*
     * Método do ciclo de vida do fragmento somente
     * utilizado aqui, como hackcode, para permitir
     * a atualização do título da toolbar sem que
     * seja lançado um erro em tempo de execução.
     * */
    override fun onResume() {
        super.onResume()

        (activity as MainActivity)
            .updateToolbarTitleInFragment(TITLE_ID)
    }


    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.tv_instagram,
            R.id.iv_instagram -> {
                openNetwork(
                    "com.instagram.android",
                    "http://instagram.com/_u/cbf_futebol",
                    "http://instagram.com/cbf_futebol"
                )
            }
            R.id.tv_facebook,
            R.id.iv_facebook -> {
                openNetwork(
                    "com.facebook.katana",
                    "fb://facewebmodal/f?href=https://www.facebook.com/thiengoCalopsita",
                    "https://www.facebook.com/thiengoCalopsita"
                )
            }
            R.id.tv_twitter,
            R.id.iv_twitter -> {
                openNetwork(
                    "com.twitter.android",
                    "https://twitter.com/thiengoCalops",
                    "https://twitter.com/thiengoCalops"
                )
            }
            R.id.tv_youtube,
            R.id.iv_youtube -> {
                openNetwork(
                    "com.google.android.youtube",
                    "https://www.youtube.com/user/thiengoCalopsita",
                    "https://www.youtube.com/user/thiengoCalopsita"
                )
            }
            else -> {
                openNetwork(
                    "com.linkedin.android",
                    "https://www.linkedin.com/in/vin%C3%ADcius-thiengo-5179b180/",
                    "https://www.linkedin.com/in/vin%C3%ADcius-thiengo-5179b180/"
                )
            }
        }
    }


    private fun openNetwork(
        appPackage: String,
        appAddress: String,
        webAddress: String
    ) {

        val uri = Uri.parse(appAddress)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        intent.setPackage(appPackage)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            /*
             * Se não houver o aplicativo da rede
             * social acionada, então abra a página
             * no navegador padrão do aparelho, Web.
             * */
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(webAddress)
                )
            )
        }
    }


}
