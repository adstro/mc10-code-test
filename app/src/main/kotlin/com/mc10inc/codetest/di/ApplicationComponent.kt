package com.mc10inc.codetest.di

import com.mc10inc.codetest.login.LoginActivity
import com.mc10inc.codetest.studylist.StudyListActivity
import com.mc10inc.codetest.studylist.StudyListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun inject(target: StudyListActivity)
    fun inject(target: LoginActivity)
    fun inject(taget: StudyListViewModel)
}