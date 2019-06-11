package com.example.myar02.AR;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myar02.R;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // le fragments
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private ArFragment arFragment;
    private ModelRenderable modeleRenderable1,modeleRenderable2,modeleRenderable3,modeleRenderable4;
    private FloatingActionButton fb_remov ;
    // thumlains ivag view
    ImageView iv_model1,iv_model2,iv_model3,iv_model4;
    View arrayView[] ;
    int selected = 1 ;
    ViewRenderable prix_model ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.activity_main);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneforme_ux_fragment);
        // ini view
        iv_model1= (ImageView)findViewById(R.id.iv_modele1);
        iv_model2= (ImageView)findViewById(R.id.iv_modele2);
        iv_model3= (ImageView)findViewById(R.id.iv_modele3);
        iv_model4= (ImageView)findViewById(R.id.iv_modele4);

        setArrayView();
        // le click listner
        setClickListener();
        
        setUpModel();


//        // When you build a Renderable, Sceneform loads its resources in the background while returning
//        // a CompletableFuture. Call thenAccept(), handle(), or check isDone() before calling get().
//        ModelRenderable.builder()
//                .setSource(this, R.raw.andy)
//                .build()
//                .thenAccept(renderable -> andyRenderable = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });

        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (modeleRenderable1 == null) {
                        return;
                    }

                    // Create the Anchor.
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());
                    createModel(anchorNode,selected);
//                    removeModel(anchorNode,selected);
//                    fb_remov.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            removeModel(anchorNode,selected);
//                        }
//                    });


                    
                    
//                    // Create the transformable andy and add it to the anchor.
//                    TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
//                    andy.setParent(anchorNode);
//                    andy.setRenderable(andyRenderable);
//                    andy.select();
                });
    }

    private void createModel(AnchorNode anchorNode, int selected) {
        // on crreé le model a afficher
        if (selected==1){

            TransformableNode model1 = new TransformableNode(arFragment.getTransformationSystem());
            model1.setParent(anchorNode);
            model1.setRenderable(modeleRenderable1);
            model1.select();
            addPrix(anchorNode,model1,"90000 da");
          //  removeModel(anchorNode,model1,"model1");

        }
        if (selected==2){

            TransformableNode model2 = new TransformableNode(arFragment.getTransformationSystem());
            model2.setParent(anchorNode);
            model2.setRenderable(modeleRenderable2);
            model2.select();
            addPrix(anchorNode,model2,"12000 DA");

        }
        if (selected==3){

            TransformableNode model3 = new TransformableNode(arFragment.getTransformationSystem());
            model3.setParent(anchorNode);
            model3.setRenderable(modeleRenderable3);
            model3.select();
            addPrix(anchorNode,model3,"22000 DA");


        }
        if (selected==4){

            TransformableNode model4 = new TransformableNode(arFragment.getTransformationSystem());
            model4.setParent(anchorNode);
            model4.setRenderable(modeleRenderable4);
                model4.select();
            addPrix(anchorNode,model4,"15000 DA");


        }
    }

    private void addPrix(AnchorNode anchorNode, TransformableNode model, String prix) {

        TransformableNode viewPrix = new TransformableNode(arFragment.getTransformationSystem());
        viewPrix.setLocalPosition(new Vector3(0f,model.getLocalPosition().y+1.3f,0));
        viewPrix.setParent(anchorNode);
        viewPrix.setRenderable(prix_model);
        viewPrix.select();
        /// set le prix
        TextView tv_prix= (TextView)prix_model.getView();
        tv_prix.setText(prix);
        // click on price to remove
        tv_prix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anchorNode.setParent(null);
            }
        });
    }

    private void removeModel(AnchorNode anchorNode, int selected){
        if (selected==1){anchorNode.setParent(null);}
        if (selected==2){anchorNode.setParent(null);}
        if (selected==3){anchorNode.setParent(null);}
        if (selected==4){anchorNode.setParent(null);}


    }


    // buildinng the models and relating them with the sfa fils 
    private void setUpModel() {
        ViewRenderable.builder().setView(this,R.layout.prix_model)
                                .build()
                                .thenAccept(renderble -> prix_model=renderble);
        ModelRenderable.builder()
                .setSource(this, Uri.parse("model.sfb"))
                .build().thenAccept(renderable ->modeleRenderable1=renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "on a pas pue charger le model horse", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Bed_01.sfb"))
                .build().thenAccept(renderable ->modeleRenderable2=renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "on a pas pue charger le model capture", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,Uri.parse("couch.sfb"))
                .build().thenAccept(renderable ->modeleRenderable3=renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "on a pas pue charger le model capture", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,Uri.parse("bed2.sfb"))
                .build().thenAccept(renderable ->modeleRenderable4=renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "on a pas pue charger le model capture", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
    }

    private void setClickListener() {
        for (int i = 0 ;i<arrayView.length; i++){
            arrayView[i].setOnClickListener(this);
        }
    }

    private void setArrayView() {
        // remplire le array View avec les images a afficher dans le thumbnail
        arrayView =new View[]{iv_model1,iv_model2,iv_model3,iv_model4};

    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View view) {
        // recuperer li de limage selectionner
        if (view.getId() == R.id.iv_modele1) {
            selected = 1;
            setBackground(view.getId());
        }else if(view.getId()== R.id.iv_modele2){
            selected = 2;
            setBackground(view.getId());
        }else if(view.getId()== R.id.iv_modele3){
            selected = 3;
            setBackground(view.getId());
        }else if(view.getId()== R.id.iv_modele4){
            selected = 4;
            setBackground(view.getId());
        }
    }

    private void setBackground(int id) {
        for (int i=0 ; i<arrayView.length;i++){

            if (arrayView[i].getId()==id){
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639")); // set background for selected item
            }else {
                arrayView[i].setBackgroundColor(Color.TRANSPARENT ); // set background for unselected item

            }
        }
    }



}

