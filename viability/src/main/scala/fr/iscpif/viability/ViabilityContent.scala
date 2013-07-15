/*
 * Copyright (C) 05/07/13 Romain Reuillon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.iscpif.viability

import fr.iscpif.kdtree.structure._
import fr.iscpif.kdtree.content._
import fr.iscpif.kdtree.algorithm.Input
import scala.util.Random

trait ViabilityContent {
  implicit val relabeliser: Relabeliser[CONTENT] = (c: Content, label: Content => Boolean) => c.copy(control = None)
  case class Content(testPoint: Point, control: Option[(Point, Point)]) extends TestPoint with Control

  def buildContent(from: Point, control: Option[(Point, Point)]): CONTENT = Content(from, control)

  type CONTENT = Content
}

