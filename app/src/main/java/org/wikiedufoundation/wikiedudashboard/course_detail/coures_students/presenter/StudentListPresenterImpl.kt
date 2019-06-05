package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.presenter


import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.provider.StudentListProvider
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.view.StudentListView

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback


class StudentListPresenterImpl(private val studentListView: StudentListView, private val studentListProvider: StudentListProvider) : StudentListPresenter {

    override fun requestStudentList(url: String) {
        studentListView.showProgressBar(true)
        studentListProvider.requestStudentList(url, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                studentListView.showProgressBar(false)
                val studentListsResponse = o as StudentListResponse
                studentListView.setData(studentListsResponse)
            }

            override fun onFailure() {
                studentListView.showProgressBar(false)
                studentListView.showMessage("Unable to connect to server.")

            }
        })
    }


}
