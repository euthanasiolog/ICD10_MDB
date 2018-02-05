package com.dev.piatr.icd_10mdb;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SavingService extends Service {
    public SavingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
