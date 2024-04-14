package com.api.backend;

import com.api.backend.config.EncoderConfig;
import com.api.backend.helper.CommonHelper;
import com.api.backend.models.entities.*;
import com.api.backend.models.services.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class Seeder {

    @Autowired
    private UserService UserService;

    @Autowired
    private ReferenceService ReferenceService;

    @Autowired
    private ContactService ContactService;

    @Autowired
    private CustomerService CustomerService;

    @Autowired
    private FaqService FaqService;

    @Autowired
    private ServiceService ServiceService;

    @Autowired
    private SliderService SliderService;

    @Autowired
    private TeamService TeamService;

    @Autowired
    private TestimonialService TestimonialService;

    @Autowired
    private PortfolioService PortfolioService;

    @Autowired
    private PortfolioImageService PortfolioImageService;

    @Autowired
    private ArticleService ArticleService;

    @Autowired
    private ArticleCommentService ArticleCommentService;

    @EventListener
    public void run(ContextRefreshedEvent event) throws Exception {
        this.CreateUser();
        this.CreateReference();
        this.CreateContact();
        this.CreateCustomer();
        this.CreateFaq();
        this.CreateService();
        this.CreateSlider();
        this.CreateTeam();
        this.CreateTestimonial();
        this.CreatePortofolio();
        this.CreateArticle();
    }

    private void CreateUser() throws ParseException {
        long TotalRows = UserService.TotalRows();
        if (TotalRows == 0) {
            Faker faker = new Faker();
            EncoderConfig enc = new EncoderConfig();
            for (int i = 1; i <= 10; i++) {
                int max = 2;
                int min = 1;
                int genderIndex = min + (int) (Math.random() * ((max - min) + 1));
                String Gender = genderIndex == 1 ? "M" : "F";
                UUID uuid = UUID.randomUUID();
                User user = new User();
                user.setEmail(faker.internet().emailAddress());
                user.setPhone(faker.phoneNumber().cellPhone());
                user.setPassword(enc.passwordEncoder().encode("p4ssw0rd!"));
                user.setConfirmToken(uuid.toString());
                user.setFirstName(faker.name().firstName());
                user.setLastName(faker.name().lastName());
                user.setGender(Gender);
                user.setCountry(faker.address().country());
                user.setAddress(faker.address().fullAddress());
                user.setAboutMe(faker.lorem().paragraph());
                user.setCreatedAt(CommonHelper.DateNow());
                user.setUpdatedAt(CommonHelper.DateNow());
                user.setStatus(1);
                UserService.saveOrUpdate(user);
            }
        }
    }

    private void CreateReference() throws ParseException {
        long TotalRows = ReferenceService.TotalRows();
        if (TotalRows == 0) {
            String[] articles = {
                    "Health and wellness",
                    "Technology and gadgets",
                    "Business and finance",
                    "Travel and tourism",
                    "Lifestyle and fashion"
            };

            String[] tags = {
                    "Mental Health",
                    "Fitness and Exercise",
                    "Alternative Medicine",
                    "Artificial Intelligence",
                    "Network Security",
                    "Cloud Computing",
                    "Entrepreneurship",
                    "Personal Finance",
                    "Marketing and Branding",
                    "Travel Tips and Tricks",
                    "Cultural Experiences",
                    "Destination Guides",
                    "Beauty and Fashion Trends",
                    "Celebrity News and Gossip",
                    "Parenting and Family Life",
            };

            String[] portfolios = {
                    "3D Modeling",
                    "Web Application",
                    "Mobile Application",
                    "Illustrator Design",
                    "UX Design"
            };

            Faker faker = new Faker();

            for(String a: articles){
                Reference ar = new Reference();
                ar.setName(a);
                ar.setSlug(CommonHelper.slugify(a));
                ar.setDescription(faker.lorem().sentence());
                ar.setType(1);
                ar.setStatus(1);
                ar.setCreatedAt(CommonHelper.DateNow());
                ar.setUpdatedAt(CommonHelper.DateNow());
                ReferenceService.saveOrUpdate(ar);
            }

            for(String t: tags){
                Reference at = new Reference();
                at.setName(t);
                at.setSlug(CommonHelper.slugify(t));
                at.setDescription(faker.lorem().sentence());
                at.setType(2);
                at.setStatus(1);
                at.setCreatedAt(CommonHelper.DateNow());
                at.setUpdatedAt(CommonHelper.DateNow());
                ReferenceService.saveOrUpdate(at);
            }

            for(String p: portfolios){
                Reference ap = new Reference();
                ap.setName(p);
                ap.setSlug(CommonHelper.slugify(p));
                ap.setDescription(faker.lorem().sentence());
                ap.setType(3);
                ap.setStatus(1);
                ap.setCreatedAt(CommonHelper.DateNow());
                ap.setUpdatedAt(CommonHelper.DateNow());
                ReferenceService.saveOrUpdate(ap);
            }

        }
    }

    private void CreateContact() throws ParseException {
        Long TotalRows = ContactService.TotalRows();
        if(TotalRows == 0)
        {
            Faker faker = new Faker();
            for(int i = 1; i <= 10; i++)
            {
                Contact contact = new Contact();
                contact.setName(faker.name().name());
                contact.setEmail(faker.internet().emailAddress());
                contact.setSubject(faker.lorem().sentence());
                contact.setMessage(faker.lorem().paragraph());
                contact.setStatus(0);
                contact.setCreatedAt(CommonHelper.DateNow());
                contact.setUpdatedAt(CommonHelper.DateNow());
                ContactService.saveOrUpdate(contact);
            }
        }
    }

    private void CreateCustomer() throws ParseException {
        Long TotalRows = CustomerService.TotalRows();
        if(TotalRows == 0)
        {
            Faker faker = new Faker();
            for(int i = 1; i <= 10; i++)
            {
                Customer customer = new Customer();
                customer.setImage("customer"+i+".jpg");
                customer.setName(faker.company().name());
                customer.setEmail(faker.internet().emailAddress());
                customer.setPhone(faker.phoneNumber().phoneNumber());
                customer.setAddress(faker.address().fullAddress());
                customer.setSort(i);
                customer.setStatus(1);
                customer.setCreatedAt(CommonHelper.DateNow());
                customer.setUpdatedAt(CommonHelper.DateNow());
                CustomerService.saveOrUpdate(customer);
            }
        }
    }

    private void CreateFaq() throws ParseException {
        Long TotalRows = FaqService.TotalRows();
        if(TotalRows == 0)
        {
            Faker faker = new Faker();
            for(int i = 1; i <= 10; i++)
            {
                Faq faq = new Faq();
                faq.setQuestion(faker.lorem().sentence());
                faq.setAnswer(faker.lorem().paragraph());
                faq.setSort(i);
                faq.setStatus(1);
                faq.setCreatedAt(CommonHelper.DateNow());
                faq.setUpdatedAt(CommonHelper.DateNow());
                FaqService.saveOrUpdate(faq);
            }
        }
    }

    private void CreateService() throws ParseException {
        Long TotalRows = ServiceService.TotalRows();
        if(TotalRows == 0)
        {
            String[] icons = {
                "fas fa-archive",
                "fas fa-atom",
                "fas fa-award",
                "fas fa-balance-scale",
                "fas fa-blender",
                "fas fa-book-reader",
                "fas fa-box-open",
                "fas fa-cash-register",
                "fas fa-cloud-download-alt",
            };

            Faker faker = new Faker();
            int i = 0;
            for(String icon: icons)
            {
                Service service = new Service();
                service.setIcon(icon);
                service.setTitle(faker.lorem().sentence());
                service.setDescription(faker.lorem().paragraph());
                service.setStatus(1);
                service.setSort(i);
                service.setCreatedAt(CommonHelper.DateNow());
                service.setUpdatedAt(CommonHelper.DateNow());
                ServiceService.saveOrUpdate(service);
                i++;
            }

        }
    }

    private void CreateSlider() throws ParseException {
        Long TotalRows = SliderService.TotalRows();
        if(TotalRows == 0)
        {
            Faker faker = new Faker();
            for(int i = 1; i <= 5; i++)
            {
                Slider slider = new Slider();
                slider.setImage("slider"+i+".jpg");
                slider.setTitle(faker.lorem().sentence());
                slider.setDescription(faker.lorem().paragraph(2));
                slider.setSort(i);
                slider.setStatus(1);
                slider.setCreatedAt(CommonHelper.DateNow());
                slider.setUpdatedAt(CommonHelper.DateNow());
                SliderService.saveOrUpdate(slider);
            }
        }
    }

    private void CreateTeam() throws ParseException {
        Long TotalRows = TeamService.TotalRows();
        if(TotalRows == 0)
        {
            Faker faker = new Faker();
            for(int i = 1; i <= 10; i++)
            {
                Team team = new Team();
                team.setImage("team"+i+".jpg");
                team.setName(faker.name().name());
                team.setEmail(faker.internet().emailAddress());
                team.setPhone(faker.phoneNumber().phoneNumber());
                team.setPositionName(CommonHelper.capitalize(faker.company().profession()));
                team.setTwitter(faker.name().username());
                team.setFacebook(faker.name().username());
                team.setLinkedIn(faker.name().username());
                team.setInstagram(faker.name().username());
                team.setAddress(faker.address().fullAddress());
                team.setSort(i);
                team.setStatus(1);
                team.setCreatedAt(CommonHelper.DateNow());
                team.setUpdatedAt(CommonHelper.DateNow());
                TeamService.saveOrUpdate(team);
            }
        }
    }

    private void CreateTestimonial() throws ParseException {
        Long TotalRows = TestimonialService.TotalRows();
        if(TotalRows == 0)
        {
            Faker faker = new Faker();
            List<Customer> customers = CustomerService.getAll();
            for(int i = 0; i < customers.size(); i++)
            {
                Testimonial testimonial = new Testimonial();
                testimonial.setCustomer(customers.get(i));
                testimonial.setImage("testimonial"+i+".jpg");
                testimonial.setName(faker.name().name());
                testimonial.setPosition(CommonHelper.capitalize(faker.company().profession()));
                testimonial.setQuote(faker.lorem().paragraph());
                testimonial.setSort(i+1);
                testimonial.setStatus(1);
                testimonial.setCreatedAt(CommonHelper.DateNow());
                testimonial.setUpdatedAt(CommonHelper.DateNow());
                TestimonialService.saveOrUpdate(testimonial);
            }
        }
    }

    private void CreatePortofolio() throws ParseException {
        Long TotalRows = PortfolioService.TotalRows();
        if(TotalRows == 0)
        {
            Faker faker = new Faker();

            for(int i = 1; i <=9; i++)
            {
                Reference category = ReferenceService.getRandom(3, 1).get(0);
                Customer customer = CustomerService.getRandom(1).get(0);

                Portfolio portfolio = new Portfolio();
                portfolio.setCustomer(customer);
                portfolio.setReference(category);
                portfolio.setTitle(faker.lorem().sentence());
                portfolio.setDescription(faker.lorem().paragraph());
                portfolio.setProjectDate(faker.date().birthday(1, 10));
                portfolio.setProjectUrl(faker.internet().url());
                portfolio.setSort(i);
                portfolio.setStatus(1);
                portfolio.setCreatedAt(CommonHelper.DateNow());
                portfolio.setUpdatedAt(CommonHelper.DateNow());
                PortfolioService.saveOrUpdate(portfolio);

                for(int j = 1; j <= 4; j++)
                {
                    PortfolioImage pg  = new PortfolioImage();
                    pg.setPortfolio(portfolio);
                    pg.setImage("portfolio"+j+".jpg");
                    pg.setStatus(j == 1 ? 1 : 0);
                    pg.setCreatedAt(CommonHelper.DateNow());
                    pg.setUpdatedAt(CommonHelper.DateNow());
                    PortfolioImageService.saveOrUpdate(pg);
                }


            }

        }
    }

    private void CreateArticle() throws ParseException {
        Long TotalRows = ArticleService.TotalRows();
        if(TotalRows == 0)
        {
            Faker faker = new Faker();
            List<User> users = UserService.getAll();


            for(int i = 0; i < users.size(); i++)
            {
                User user = users.get(i);
                int num = i + 1;
                String title = faker.lorem().sentence();
                String slug = CommonHelper.slugify(title);
                List<Reference> categories = ReferenceService.getRandom(1, 3);
                List<Reference> tags = ReferenceService.getRandom(2, 5);

                Set<Reference> references = new HashSet<Reference>();

                for(Reference cc: categories)
                {
                    references.add(cc);
                }

                for(Reference tag: tags)
                {
                    references.add(tag);
                }

                Article article = new Article();
                article.setReferences(references);
                article.setUser(user);
                article.setImage("article"+num+".jpg");
                article.setTitle(title);
                article.setSlug(slug);
                article.setDescription(faker.lorem().paragraph(3));
                article.setContent(faker.lorem().paragraph(10));
                article.setStatus(1);
                article.setCreatedAt(CommonHelper.DateNow());
                article.setUpdatedAt(CommonHelper.DateNow());
                ArticleService.saveOrUpdate(article);

                List<User> comments = UserService.getRandomNot(2, user.getId());
                for(User comment: comments)
                {
                    ArticleComment ac = new ArticleComment();
                    ac.setUser(comment);
                    ac.setArticle(article);
                    ac.setComment(faker.lorem().paragraph());
                    ac.setStatus(1);
                    ac.setCreatedAt(CommonHelper.DateNow());
                    ac.setUpdatedAt(CommonHelper.DateNow());
                    ArticleCommentService.saveOrUpdate(ac);
                }

            }
        }
    }
}
