package scheduling.cron

import org.quartz.CronScheduleBuilder
import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.SchedulerException
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.quartz.impl.StdSchedulerFactory
import org.quartz.JobKey
import scheduling.job.JobCall

object CronTrigger {
   var jobCall: JobDetail = null
   var triggerJobCall: Trigger = null
   
   def main(args: Array[String]) {
     jobDefinition()
     triggerDefinition()
     run()
   }
   
   def jobDefinition() {
     val jobKey1: JobKey = new JobKey("jobCall", "group1")
     jobCall = JobBuilder.newJob(classOf[JobCall]).withIdentity(jobKey1).build()
   }
   
   def triggerDefinition() {
     triggerJobCall = TriggerBuilder.newTrigger().withIdentity("triggerJobCall", "group1")
                     .withSchedule(CronScheduleBuilder.cronSchedule("0 18 15 * * ?")).build()
   }
   
   def run() {
     val scheduler: Scheduler = new StdSchedulerFactory().getScheduler()
     scheduler.start()
     scheduler.scheduleJob(jobCall, triggerJobCall)
   }
}