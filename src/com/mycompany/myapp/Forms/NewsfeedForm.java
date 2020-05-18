package com.mycompany.myapp.Forms;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.CN.getDisplayWidth;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.NORTH;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.forms.colis.AjoutColis;
import com.esprit.pidev.forms.vehicule.FindTaxi;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class NewsfeedForm extends BaseForm {

    public NewsfeedForm(Resources res) {
        super("Newsfeed", new BoxLayout(BoxLayout.Y_AXIS));

         setUIID("Maps");
        super.installSidemenu(res);
        //--------------------
        Container layer = new Container();
        Button back = new Button("TitleCommand");
        //FontImage.setMaterialIcon(back, FontImage.MATERIAL_ARROW_BACK);
        TextField from = new TextField("", "Choisir un point de départ", 40, TextField.ANY);
        TextField to = new TextField("", "Choisir un point de destination", 40, TextField.ANY);
        //TextField bt = new TextField("", "", 40, TextField.ANY);
        Label fromSelected = new Label();
        final Label toSelected = new Label();
        Button searchtaxi = new Button();
        searchtaxi.getStyle().setMarginTop(5);
        Image img1 = res.getImage("marker2.png");
        Image img = res.getImage("marker1.png");
        int height = Display.getInstance().convertToPixels(10f);
        int width = Display.getInstance().convertToPixels(10f);
        from.getHintLabel().setUIID("FromToTextFieldHint");
        from.setUIID("FromToTextField");
        to.getHintLabel().setUIID("FromToTextFieldHint");
        to.setUIID("FromToTextField");
        Container navigationToolbar = new Container();
        navigationToolbar = BoxLayout.encloseY(back, BorderLayout.centerCenterEastWest(from, new Label(img.fill(width, height)), fromSelected), BorderLayout.centerCenterEastWest(to, new Label(img1.fill(width, height)), toSelected), BorderLayout.centerCenterEastWest(null, new Button("Trouver Un Taxi"), searchtaxi));
        navigationToolbar.setUIID("WhereToToolbar");
        navigationToolbar.getUnselectedStyle().setBgPainter((g1, rect) -> {
            g1.setAlpha(255);
            g1.setColor(0xffffff);

        });
        layer.setLayout(new BorderLayout());
        layer.add(NORTH, navigationToolbar);
        navigationToolbar.setWidth(getDisplayWidth());
        navigationToolbar.setHeight(getPreferredH());
        navigationToolbar.setY(-navigationToolbar.getHeight());
        layer.animateLayout(200);

        //---------------------
        Container cnt = addButton(res.getImage("news-item-1.jpg"), "Reserver Taxi", false, 26, 32);
        Container cnt1 = addButton(res.getImage("news-item-2.jpg"), "Reserver Covoiturage", true, 15, 21);
        Container cnt2 = addButton(res.getImage("news-item-3.jpg"), "Envoyer Colis", false, 36, 15);
        TextField Depart1 = new TextField();
        TextField Destination1 = new TextField();
        TextField Depart2 = new TextField();
        TextField Destination2 = new TextField();

        //***************colis*********************
        Container coli_layer = new Container();
        Button back0 = new Button("TitleCommand");
        //FontImage.setMaterialIcon(back, FontImage.MATERIAL_ARROW_BACK);
        TextField from_coli = new TextField("", "Départ Coli", 40, TextField.ANY);
        TextField to_coli = new TextField("", "Destination Coli", 40, TextField.ANY);
        //TextField bt = new TextField("", "", 40, TextField.ANY);
        Label fromSelected_coli = new Label();
        final Label toSelected_coli = new Label();
        Button searchcolis = new Button();
        searchcolis.getStyle().setMarginTop(5);;
        from_coli.getHintLabel().setUIID("FromToTextFieldHint");
        from_coli.setUIID("FromToTextField");
        to_coli.getHintLabel().setUIID("FromToTextFieldHint");
        to_coli.setUIID("FromToTextField");
        Container colis = new Container();
        colis = BoxLayout.encloseY(back0, BorderLayout.centerCenterEastWest(from_coli, new Label(img.fill(width, height)), fromSelected_coli), BorderLayout.centerCenterEastWest(to_coli, new Label(img1.fill(width, height)), toSelected_coli), BorderLayout.centerCenterEastWest(null, new Button("Envoyer coli"), searchcolis));
        colis.setUIID("WhereToToolbar");
        colis.getUnselectedStyle().setBgPainter((g2, rect) -> {
            g2.setAlpha(255);
            g2.setColor(0xffffff);

        });
        coli_layer.setLayout(new BorderLayout());
        coli_layer.add(NORTH, colis);
        colis.setWidth(getDisplayWidth());
        colis.setHeight(getPreferredH());
        colis.setY(-colis.getHeight());
        coli_layer.animateLayout(200);

        //************************************
        //********************covoiturage***********************
        Container cov_layer = new Container();
        Button back1 = new Button("TitleCommand");
        //FontImage.setMaterialIcon(back, FontImage.MATERIAL_ARROW_BACK);
        TextField from_cov = new TextField("", "Départ Covoiturage", 40, TextField.ANY);
        TextField to_cov = new TextField("", "Destination Covoiturage", 40, TextField.ANY);
        //TextField bt = new TextField("", "", 40, TextField.ANY);
        Label fromSelected_cov = new Label();
        final Label toSelected_cov = new Label();
        Button searchcov = new Button();
        searchcov.getStyle().setMarginTop(5);
        from_cov.getHintLabel().setUIID("FromToTextFieldHint");
        from_cov.setUIID("FromToTextField");
        to_cov.getHintLabel().setUIID("FromToTextFieldHint");
        to_cov.setUIID("FromToTextField");
        Container covs = new Container();
        covs = BoxLayout.encloseY(back1, BorderLayout.centerCenterEastWest(from_cov, new Label(img.fill(width, height)), fromSelected_cov), BorderLayout.centerCenterEastWest(to_cov, new Label(img1.fill(width, height)), toSelected_cov), BorderLayout.centerCenterEastWest(null, new Button("Trouver Covoiturage"), searchcov));
        covs.setUIID("WhereToToolbar");
        covs.getUnselectedStyle().setBgPainter((g3, rect) -> {
            g3.setAlpha(255);
            g3.setColor(0xffffff);

        });
        cov_layer.setLayout(new BorderLayout());
        cov_layer.add(NORTH, covs);
        covs.setWidth(getDisplayWidth());
        covs.setHeight(getPreferredH());
        covs.setY(-covs.getHeight());
        covs.animateLayout(200);

//        Container dep1 = BorderLayout.centerEastWest(Depart1, new Label(img.fill(width, height)), null);
//        Container dest1 = BorderLayout.centerEastWest(Destination1, new Label(img.fill(width, height)), null);
//        Container dep2 = BorderLayout.centerEastWest(Depart2, new Label(img.fill(width, height)), null);
//        Container dest2 = BorderLayout.centerEastWest(Destination2, new Label(img.fill(width, height)), null);
        Tabs swipe = new Tabs();

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Tout", barGroup);
        all.setUIID("SelectBar");
        RadioButton Services = RadioButton.createToggle("Taxi", barGroup);
        Services.setUIID("SelectBar");
        RadioButton Events = RadioButton.createToggle("Colis", barGroup);
        Events.setUIID("SelectBar");
        RadioButton Blog = RadioButton.createToggle("Co-Voiturage", barGroup);
        Blog.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, Services, Events, Blog),
                FlowLayout.encloseBottom(arrow)
        ));
        Services.addActionListener(l -> {
            if (Services.isSelected()) {
                System.out.println("Taxi");
                this.removeComponent(cnt);
                this.removeComponent(cnt1);
                this.removeComponent(cnt2);
                this.removeComponent(from_coli);
                this.removeComponent(to_coli);
                this.removeComponent(searchcolis);
                this.removeComponent(from_cov);
                this.removeComponent(to_cov);
                this.removeComponent(searchcov);
                searchtaxi.setText("Trouver Taxi");
                this.addAll(from,to,searchtaxi);
                this.refreshTheme();
            }
        });

        Events.addActionListener(l -> {
            if (Events.isSelected()) {
                System.out.println("Colis");
                this.removeComponent(cnt);
                this.removeComponent(cnt1);
                this.removeComponent(cnt2);
                this.removeComponent(from);
                this.removeComponent(to);
                this.removeComponent(searchtaxi);
                this.removeComponent(from_cov);
                this.removeComponent(to_cov);
                this.removeComponent(searchcov);
                searchcolis.setText("Envoyer Colis");
                this.addAll(from_coli,to_coli, searchcolis);
                this.refreshTheme();
            }
        });
        searchcolis.addActionListener(l -> {
            new AjoutColis(res).show();
            //new AfficherVehicule(res).show();
        });
        searchtaxi.addActionListener(l -> {
            new FindTaxi(res, from.getText(), to.getText()).show();
        });
        all.addActionListener(l -> {
            if (all.isSelected()) {
                this.removeComponent(from);
                this.removeComponent(to);
                this.removeComponent(searchtaxi);
                this.removeComponent(from_coli);
                this.removeComponent(to_coli);
                this.removeComponent(searchcolis);
                this.removeComponent(from_cov);
                this.removeComponent(to_cov);
                this.removeComponent(searchcov);
                this.addAll(cnt, cnt1, cnt2);
                this.refreshTheme();
                System.out.println("all");
            }
        });
        Blog.addChangeListener(l
                -> {
            System.out.println("co-voiturage");
            if (Blog.isSelected()) {
                this.removeComponent(cnt);
                this.removeComponent(cnt1);
                this.removeComponent(cnt2);
                this.removeComponent(from);
                this.removeComponent(to);
                this.removeComponent(searchtaxi);
                this.removeComponent(from_coli);
                this.removeComponent(to_coli);
                this.removeComponent(searchcolis);
                searchcov.setText("Trouver Co-Voiturage");
                this.addAll(from_cov, to_cov, searchcov);
                this.refreshTheme();
            }
        });
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private Container addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));

        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
        return cnt;
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
