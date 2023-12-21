package com.example.petforyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdvertisements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_advertisements);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AdvertisementModel> adsList = new ArrayList<>();

        ApiService apiService = Api.getInsecureClient().create(ApiService.class);
        Call<List<Advertisement>> call = apiService.getAdvertisementsByUser(4);

        call.enqueue(new Callback<List<Advertisement>>() {
            @Override
            public void onResponse(Call<List<Advertisement>> call, Response<List<Advertisement>> response) {
                if (response.isSuccessful()) {
                    System.out.println("11111111111111111111111111111");


                    List<Advertisement> advertisements = response.body();
                    for (Advertisement advertisement : advertisements) {
                        AdvertisementInfoModel infoModel = advertisement.getAdvertisementInfo();
                        AdvertisementLocationModel locationModel = advertisement.getAdvertisementLocation();

                        adsList.add(0, new AdvertisementModel(
                                R.drawable.cat2,
                                advertisement.getTitle(),
                                locationModel.getRegion(),
                                advertisement.getPublicationDate(),
                                Double.toString(infoModel.getPrice())
                        ));


                    }
                    // После завершения итерации по объявлениям обновите RecyclerView с вашим адаптером
                    //adapter.notifyDataSetChanged();
                    AdvertisementAdapter adapter = new AdvertisementAdapter(adsList);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(advertisement -> {
                        for (Advertisement adv : advertisements) {
                            AdvertisementModel advModel = new AdvertisementModel(
                                    R.drawable.cat2,
                                    adv.getTitle(),
                                    adv.getAdvertisementLocation().getRegion(),
                                    adv.getPublicationDate(),
                                    Double.toString(adv.getAdvertisementInfo().getPrice())
                           );

                            if(advModel.getTitle().equals(advertisement.getTitle())) {
                                Intent intent = new Intent(MyAdvertisements.this, DeleteActivity.class);
                                intent.putExtra("advertisementId", adv.getId()); // передача информации об объявлении
                                startActivity(intent);
                            }

                        }
                        // Обработка клика на объявлении
                        // Здесь вы можете запустить новую активность, передавая информацию об объявлении

                    });

                    //System.out.println("11111111111111111111111");
                    //System.out.println("adsList = "  + adsList.size());
                } else {
                    System.out.println("2222222222222222222222222222");
                    // Обработка ошибки
                    Toast.makeText(getApplicationContext(),
                            "Ошибка при получении объявлений", Toast.LENGTH_SHORT).show();
                    //System.out.println("2222222222222222222");
                }
            }

            @Override
            public void onFailure(Call<List<Advertisement>> call, Throwable t) {
                System.out.println("333333333333333333333333");
                // Обработка ошибки при выполнении запроса
                Toast.makeText(getApplicationContext(),
                        "Ошибка сети", Toast.LENGTH_SHORT).show();
                // System.out.println("3333333333333333333333333");
                //System.out.println(t.getMessage());
            }
        });
    }
}