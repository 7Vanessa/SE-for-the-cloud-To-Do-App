# BackEnd Application

## Application Architecture 

The Back-End application is splitted in 2 parts :
 - Student presentation related
 - Report validation related

### Student presentation

- Student proposes 3 dates for his defense
  - url: `/propose_dates`
  - method: `POST`
  - data: form(studentID, dates, presentation infos, mail)
- Teacher / tutor retrieve the list of presentation dates
  - url: `/get_presentation/mail`
  - method: `GET`
  - data: dates, studentID ?, infos ?, defenseID
- Teacher / tutor send their availabilities
  - url: `/send_available/defenseID`
  - method: `POST`
  - data: dates

### Report validation

- Student sends his report as pdf
  - url: `/send_report`
  - method: `POST`
  - data: file (blob), tutorID, teacherID
- Teacher / tutor retrieve the student's report
  - url: `/get_report/ID`
  - method: `GET`
  - data: file, reportID
- Teacher / tutor validate or not the report
  - url: `/validate/reportID`
  - method: `POST`
  - data: boolean, ID
