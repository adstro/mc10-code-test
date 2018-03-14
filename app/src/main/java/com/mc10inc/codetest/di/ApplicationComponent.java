package com.mc10inc.codetest.di;

import com.mc10inc.codetest.login.LoginActivity;
import com.mc10inc.codetest.studylist.StudyListActivity;
import com.mc10inc.codetest.studylist.StudyListViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    public void inject(StudyListActivity target);
    public void inject(LoginActivity target);
    public void inject(StudyListViewModel target);
}
