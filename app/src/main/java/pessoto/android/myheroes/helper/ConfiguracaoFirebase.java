package pessoto.android.myheroes.helper;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth referenciaAutenticacao;

    //retorna a instancia do FireBaseAuth
    public static FirebaseAuth getFirebaseAutenticacao() {
        if (referenciaAutenticacao == null) {
            referenciaAutenticacao = FirebaseAuth.getInstance();
        }
        return referenciaAutenticacao;
    }
}
