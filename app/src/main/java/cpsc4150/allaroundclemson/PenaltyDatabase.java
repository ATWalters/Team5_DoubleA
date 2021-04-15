package cpsc4150.allaroundclemson;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class PenaltyDatabase {
    private static PenaltyDatabase mPenaltyDatabase;
    private List<Penalty> mPenalty;

    public static PenaltyDatabase get(Context context){
        if(mPenaltyDatabase == null){
            mPenaltyDatabase = new PenaltyDatabase(context);
        }
        return mPenaltyDatabase;
    }

    private PenaltyDatabase(Context context){
        mPenalty = new ArrayList<>();
        Resources res = context.getResources();
        String [] names = res.getStringArray(R.array.penalty_name);
        String [] descs = res.getStringArray(R.array.penalty_description);
        for(int i = 0; i < names.length; i++){
            mPenalty.add(new Penalty(i + 1, names[i], descs[i]));
        }
    }

    public List<Penalty> getPenalties(){
        return this.mPenalty;
    }

    public Penalty getPenalty(int penaltyId){
        for(Penalty penalty : mPenalty){
            if(penalty.getId() == penaltyId){
                return penalty;
            }
        }
        return null;
    }
}
