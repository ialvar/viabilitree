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

package viabilitree.viability

import monocle.macros.Lenses
import viabilitree.kdtree.structure._

object ControlledDynamicContent {
  def reduce: ContentReduction[ControlledDynamicContent] =
    (c1: Leaf[ControlledDynamicContent], c2: Leaf[ControlledDynamicContent]) => Some(c1.content)

  def maximalReduction(criticalLeaves: Set[Path]): ContentReduction[ControlledDynamicContent] =
    (c1: Leaf[ControlledDynamicContent], c2: Leaf[ControlledDynamicContent]) =>
      (criticalLeaves.contains(c1.path), criticalLeaves.contains(c2.path)) match {
        case (true, false) => Some(c1.content)
        case (false, true) => Some(c2.content)
        case (true, true) => None
        case (false, false) => Some(c1.content)
      }

  def reduceFalse(criticalLeaves: Set[Path]): ContentReduction[ControlledDynamicContent] =
    (c1: Leaf[ControlledDynamicContent], c2: Leaf[ControlledDynamicContent]) =>
      (c1.content.label, c2.content.label) match {
        case (false, false) => maximalReduction(criticalLeaves)(c1, c2)
        case _ => None
      }
}

@Lenses case class ControlledDynamicContent(
  testPoint: Vector[Double],
  control: Option[Int],
  resultPoint: Option[Vector[Double]],
  label: Boolean,
  controlMax: Int)

