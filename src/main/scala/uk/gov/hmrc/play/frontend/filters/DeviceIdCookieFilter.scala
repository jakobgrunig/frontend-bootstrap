/*
 * Copyright 2015 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.play.frontend.filters

import play.api.{Logger, Play}
import play.api.Play.current
import uk.gov.hmrc.play.audit.http.connector.AuditConnector
import uk.gov.hmrc.play.filters.frontend.DeviceIdFilter

class DeviceIdCookieFilter(val appName: String, val auditConnector: AuditConnector) extends DeviceIdFilter {

  final val configId = "cookie.deviceId.secret"
  final val message = "Missing required configuration entry for deviceIdFilter :"

  override lazy val secret: String = Play.configuration.getString(configId).getOrElse {
    Logger.error(s"$message $configId")
    throw new SecurityException(s"$message $configId")
  }

}

object DeviceIdCookieFilter {
  def apply(appName: String, auditConnector: AuditConnector) = new DeviceIdCookieFilter(appName, auditConnector)
}
