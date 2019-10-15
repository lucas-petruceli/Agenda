package pucminas.br.agenda;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AgendaAdapter extends FragmentStatePagerAdapter {

    private static final int CADASTRAR = 0;
    private static final int LISTAR = 1;
    private static final int ATUALIZAR = 2;
    private String[] TAB_TITLES;
    private Context ctx;

    AgendaAdapter(@NonNull FragmentManager fm, Context ctx, String[] title) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.TAB_TITLES = title;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment f;

        switch (position) {
            case CADASTRAR:
                CadastrarFragment cf = new CadastrarFragment();
                f = cf;
                break;
            case LISTAR:
                ListarFragment lf = new ListarFragment();
                f = lf;
                break;
            case ATUALIZAR:
                AtualizarFragment af = new AtualizarFragment();
                f = af;
                break;
            default:
                f = null;
        }

        return f;
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;

        switch (position) {
            case 0:
                title = TAB_TITLES[position];
                break;
            case 1:
                title = TAB_TITLES[position];
                break;
            case 2:
                title = TAB_TITLES[position];
                break;
            default:
                title = "";
        }

        return title;
    }

}
